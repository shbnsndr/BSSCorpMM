package com.bss.ctrl

import com.bss.domain.Expenses;
import com.bss.domain.LoanAndLiabilities
import com.bss.domain.User
import com.bss.domain.UserAccounts;
import com.bss.domain.Vehicle;
import com.bss.domain.VehicleMileage;
import com.bss.googleutils.GoogleSheetWrapper;
import com.bsscorp.utils.Constants;

class ExpenseController {

    def index() {
		
		String userId = session["userId"]
		
		UserAccounts[] accounts = UserAccounts.findAll{ user.id ==  userId && effectiveToDate == null}
		Vehicle[] vehicles = Vehicle.findAll { user.id == userId}
		
		LoanAndLiabilities[] loans = LoanAndLiabilities.findAll{user.id == userId && actualReturnDate == null && type == "Loan"}
		LoanAndLiabilities[] lendeds = LoanAndLiabilities.findAll{user.id == userId && actualReturnDate == null && type == "Lend"}
		
		render(view:"index.gsp", model:[userAccounts:accounts, vehicles:vehicles, loans:loans, lendeds:lendeds])
		
	}
	
	def addExpense() {
		
		String userId = session["userId"];
		User user = User.find {id == userId}
		
		String expenseName = params["expenseName"]
		String expenseType = params["expenseType"]
		String account = params["account"]
		String amount = params["amount"]
		
		double expenseAmount = amount.toDouble()
		
		UserAccounts userAccount = UserAccounts.find { id == account}
		Expenses expense = new Expenses(user:user, userAccount:userAccount)
		expense.setRemarks(expenseName)
		expense.setTxnType(expenseType)
		expense.setAmount(expenseAmount)
		expense.setCreatedBy(user.name)
		expense.setCreatedDate(new Date())
		
		String msg = "", flag="0"
		
		expense.validate()
		if (expense.hasErrors()) {
			expense.errors.allErrors.each {
				msg = 'Expense not added due to ' + it
				redirect(controller:"Dashboard", params:['msg':msg,'flag':flag])
				return
			}
		}
		boolean res = true
		
		switch(expenseType){
			case "Fuel":
			
				String vehicleNumber = params["vehicleNumber"]
				String fuelPrice = params["fuelPrice"]
				String kmsRun = params["kmsRun"]
				String totalCost = expenseAmount
				
				Vehicle v = Vehicle.find{ id == vehicleNumber}
				if(v == null){
					msg = 'Invalid Fuel Expense'
					redirect(controller:"Dashboard", params:['msg':msg,'flag':flag])
					return
				}
				VehicleMileage lastMillage = VehicleMileage.find {vehicle == v && kmRun == 0 && kmAfterInflation == 0 }
				VehicleMileage newEntry = new VehicleMileage(vehicle:v)
				double fuelPricePerLitre = fuelPrice.toDouble()
				double cost = totalCost.toDouble()
				double litresPurchased = cost/fuelPricePerLitre
				double kmsRunBeforeInfliation = kmsRun.toDouble()
				newEntry.setInfliatedDate(new Date())
				newEntry.setPricePerLitre(fuelPricePerLitre)
				newEntry.setCost(cost)
				newEntry.setLitresPurchased(litresPurchased)
				newEntry.setKmBeforeInflation(kmsRunBeforeInfliation)
				
				if(lastMillage != null){
					lastMillage.setKmAfterInflation(kmsRunBeforeInfliation)
					double kmRun = (kmsRunBeforeInfliation - lastMillage.kmBeforeInflation)
					double mileage = kmRun/lastMillage.litresPurchased
					lastMillage.setKmRun(kmRun)
					lastMillage.setMillageAchieved(mileage)
					lastMillage.save(flush: true, failOnError: true);
				}
				
				
				res = newEntry.save()
				
				break;
				
			case "Loan":
				
				String loanDescription = params["loanDescription"]
				String loanFrom = params["loanFrom"]
				String loanPlannedReturnDate = params["loanPlannedReturnDate"]
				
				LoanAndLiabilities lal = new LoanAndLiabilities(user:user)
				lal.setDate(new Date())
				lal.setType("Loan")
				lal.setDescription(loanDescription)
				lal.setFromToPerson(loanFrom)
				lal.setAmount(expenseAmount)
				Date returnDate = Constants.convertStringToDate(loanPlannedReturnDate, null)
				lal.setPlannedReturnDate(returnDate)
				res = lal.save()
				expenseAmount = expenseAmount*-1
				break;
			
			case "Lend":
				
				String lendedDescription = params["lendedDescription"]
				String lendedTo = params["lendedTo"]
				String lendedPlannedReturnDate = params["lendedPlannedReturnDate"]
				
				LoanAndLiabilities lal = new LoanAndLiabilities(user:user)
				lal.setDate(new Date())
				lal.setType("Lend")
				lal.setDescription(lendedDescription)
				lal.setFromToPerson(lendedTo)
				lal.setAmount(expenseAmount)
				Date returnDate = Constants.convertStringToDate(lendedPlannedReturnDate, null)
				lal.setPlannedReturnDate(returnDate)
				res = lal.save()
				
				break;
		
			case "Transfer Within Accounts":
				
				String transfersAccountNewExisting = params["transfersAccountNewExisting"]
				
				if(transfersAccountNewExisting.equals("New")){
					
					String transfersAccountName = params["transfersAccountName"]
					String transfersAccountType = params["transfersAccountType"]
					
					
					UserAccounts newAccount = new UserAccounts(user:user)
					newAccount.setAccountName(transfersAccountName)
					newAccount.setAccountType(transfersAccountType)
					newAccount.setCurrentBalance(expenseAmount)
					newAccount.setEffectiveFromDate(new Date())
					newAccount.setRemarks("Opening Balance")
					
					res = newAccount.save()
					
				}else{
					String transfersAccount = params["transfersAccount"]
					UserAccounts existingAccount = UserAccounts.find { id == transfersAccount}
					double newBalance = existingAccount.currentBalance + expenseAmount
					existingAccount.setCurrentBalance(newBalance)
					
					res = existingAccount.save(flush: true, failOnError: true)
				}
				
				break;
			case "Pay Debts":
				String loanId = params["debtLoanAccount"]
				LoanAndLiabilities lal = LoanAndLiabilities.find { id == loanId}
				double loanAmount = lal.amount - expenseAmount
				lal.setAmount(loanAmount)
				if(loanAmount == 0){
					lal.setActualReturnDate(new Date())
				}
				res = lal.save(flush: true, failOnError: true)
				break;
			case "Receive Debts":
				String lendId = params["debtLendAccount"]
				LoanAndLiabilities lal = LoanAndLiabilities.find { id == lendId}
				double lendAmount = lal.amount - expenseAmount
				lal.setAmount(lendAmount)
				if(lendAmount == 0){
					lal.setActualReturnDate(new Date())
				}
				res = lal.save(flush: true, failOnError: true)
			case "Income":
			case "Income From Other Sources":
				expenseAmount = expenseAmount*-1
				break;
			
			default:
				break;
		}
		
		
		
		if(res){
			double newBalance = userAccount.currentBalance - expenseAmount
			userAccount.setCurrentBalance(newBalance)
			userAccount.save(flush: true, failOnError: true)
			expense.save()
			msg = "Expense added"
			flag = "1"
			
		}else{
			msg = "Couldn't save the expense";
			flag = "0"
		}
		redirect(controller:"Dashboard", params:['msg':msg,'flag':flag])
	}
	
	def getExpenseReport(){
		
		String userId = session["userId"]
		String strFromDate = params['fromDate']
		String strToDate = params['toDate']
		
		Date fromDate = new Date()-30
		Date toDate = new Date()
		
		if(strFromDate != null){
			fromDate = Constants.convertStringToDate(strFromDate, null)
		}
		
		if(strToDate != null){
			toDate = Constants.convertStringToDate(strToDate, null)
		}
		
		toDate = toDate+1
		
		Expenses[] expenses = Expenses.findAll {user.id == userId && createdDate >= fromDate && createdDate <= toDate}
		
		render(view:"report.gsp", model:[expenses:expenses])
		
	}
	
	def uploadReportToGoogle(){
		
		String msg = "Report uploaded to Google Server"
		String flag = "1"
		
		String userId = session["userId"]
		User user = User.find { id == userId }
		String strFromDate = params['fromDate']
		String strToDate = params['toDate']
		
		Date fromDate = new Date()-30
		Date toDate = new Date()
		
		if(strFromDate != null){
			fromDate = Constants.convertStringToDate(strFromDate, null)
		}
		
		if(strToDate != null){
			toDate = Constants.convertStringToDate(strToDate, null)
		}
		
		toDate = toDate+1
		
		Expenses[] expenses = Expenses.findAll {user.id == userId && createdDate >= fromDate && createdDate <= toDate}
		
		Map<String, List<List<Object>>> dataMap = new HashMap<String, List<List<Object>>>()
		List<List<Object>> dataSheet = new ArrayList<List<Object>>()
		List dataRow = new ArrayList<Object>()
		
		List<String> sheetNames = new ArrayList<String>()
		sheetNames.add("Summary")
		sheetNames.add("Expenses")
//		sheetNames.add("Petrol Expenses")
//		sheetNames.add("Loan and Lend")
		
		//Adding Headers for the Sheet Expenses
		dataRow.add("Date")
		dataRow.add("Accout")
		dataRow.add("Accout type")
		dataRow.add("Expense type")
		dataRow.add("Description")
		dataRow.add("Amount")
		
		dataSheet.add(dataRow)
		
		Map<String, Double> expenseSummary = new HashMap<String, Double>()

		//Iterate and add amount to List
		for (expense in expenses) {
			dataRow = new ArrayList<Object>()
			
			dataRow.add(expense.createdDate.format(Constants.STR_DATE_FORMAT))
			dataRow.add(expense.userAccount.accountName)
			dataRow.add(expense.userAccount.accountType)
			String txnType = expense.txnType
			dataRow.add(txnType)
			dataRow.add(expense.remarks)
			double amount = expense.amount
			dataRow.add(String.valueOf(amount))
			
			if(expenseSummary.get(txnType) == null){
				expenseSummary.put(txnType, amount)
			}else{
				double sum = expenseSummary.get(txnType)+amount
				expenseSummary.put(txnType, sum)
			}
			
			dataSheet.add(dataRow)
		}		
		
		dataMap.put("Expenses", dataSheet)
		
		dataSheet = new ArrayList<List<Object>>()
		//Adding headers for sheet Summary
		dataRow = new ArrayList<Object>()
		dataRow.add("Expense Type")
		dataRow.add("Amount")
		dataSheet.add(dataRow)
		
		//Iterating and adding other data to Summary Sheet
		for (key in expenseSummary.keySet()) {
			dataRow = new ArrayList<Object>()
			dataRow.add(key)
			dataRow.add(String.valueOf(expenseSummary.get(key)))
			dataSheet.add(dataRow)
		}
		
		dataMap.put("Summary", dataSheet)
		
		
		Vehicle[] vehicles = Vehicle.findAll{user.id == userId}
		LoanAndLiabilities[] loanAndLiabilities = LoanAndLiabilities.findAll{user.id == userId && actualReturnDate == null}
		
		sheetNames.add("Loan and Liabilities")
		
		dataSheet = new ArrayList<List<Object>>()
		//Adding headers for sheet Loan and Liabilities
		dataRow = new ArrayList<Object>()
		dataRow.add("Date")
		dataRow.add("Loan or Liability")
		dataRow.add("Description")
		dataRow.add("From or To Person")
		dataRow.add("Amount")
		dataRow.add("Planned Returned Date")
		dataSheet.add(dataRow)
		//Adding content
		
		for (lal in loanAndLiabilities) {
			dataRow = new ArrayList<Object>()
			dataRow.add(lal.date.format(Constants.STR_DATE_FORMAT))
			dataRow.add(lal.type)
			dataRow.add(lal.description)
			dataRow.add(lal.fromToPerson)
			dataRow.add(String.valueOf(lal.amount))
			dataRow.add(lal.plannedReturnDate.format(Constants.STR_DATE_FORMAT))
			dataSheet.add(dataRow)
		}
		
		dataMap.put("Loan and Liabilities", dataSheet)
		
		for (v in vehicles) {
			VehicleMileage[] vms = VehicleMileage.findAll {vehicle == v && infliatedDate >= fromDate && infliatedDate <= toDate}
			
			sheetNames.add(v.VehicleNumber+" Mileage")
			
			dataSheet = new ArrayList<List<Object>>()
			//Adding headers for sheet Loan and Liabilities
			dataRow = new ArrayList<Object>()
			dataRow.add("Date")
			dataRow.add("Fuel type")
			dataRow.add("Fuel Price per litre")
			dataRow.add("Litres purchased")
			dataRow.add("Cost")
			dataRow.add("KM Before")
			dataRow.add("KM After")
			dataRow.add("KM Run")
			dataRow.add("Mileage")
			dataSheet.add(dataRow)
			//Adding content
			
			for (vm in vms) {
				dataRow = new ArrayList<Object>()
				dataRow.add(vm.infliatedDate.format(Constants.STR_DATE_FORMAT))
				dataRow.add(v.engineType)
				dataRow.add(String.valueOf(vm.pricePerLitre.round(2)))
				dataRow.add(String.valueOf(vm.litresPurchased.round(2)))
				dataRow.add(String.valueOf(vm.cost))
				dataRow.add(String.valueOf(vm.kmBeforeInflation))
				dataRow.add(String.valueOf(vm.kmAfterInflation))
				dataRow.add(String.valueOf(vm.kmRun))
				dataRow.add(String.valueOf(vm.millageAchieved))
				dataSheet.add(dataRow)
			}
			
			dataMap.put(v.VehicleNumber+" Mileage", dataSheet)
		}
		
		String sheetName = user.name+"_alias_"+user.loginId+" Expense Sheet "+fromDate.format(Constants.STR_DATE_FORMAT)+" "+toDate.format(Constants.STR_DATE_FORMAT);
		
		GoogleSheetWrapper.uploadSheet(user.driveFolderId, sheetName, dataMap, sheetNames)
		
		redirect(controller:"Dashboard", params:['msg':msg,'flag':flag])
	}
}
