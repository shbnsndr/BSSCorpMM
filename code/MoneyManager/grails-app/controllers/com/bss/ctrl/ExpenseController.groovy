package com.bss.ctrl

import com.bss.domain.Expenses;
import com.bss.domain.LoanAndLiabilities
import com.bss.domain.User
import com.bss.domain.UserAccounts;
import com.bss.domain.Vehicle;
import com.bss.domain.VehicleMileage;

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
				VehicleMileage lastMillage = VehicleMileage.find {vehicle == v && kmRun == 0 }
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
				Date returnDate = new Date().parse("dd/MM/yyyy", loanPlannedReturnDate)
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
				Date returnDate = new Date().parse("dd/MM/yyyy", lendedPlannedReturnDate)
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
			fromDate = Date.parse("dd/MM/yyyy", strFromDate)
		}
		
		if(strToDate != null){
			toDate = Date.parse("dd/MM/yyyy", strToDate)
		}
		
		toDate = toDate+1
		
		Expenses[] expenses = Expenses.findAll {user.id == userId && createdDate >= fromDate && createdDate <= toDate}
		
		render(view:"report.gsp", model:[expenses:expenses])
		
	}
}
