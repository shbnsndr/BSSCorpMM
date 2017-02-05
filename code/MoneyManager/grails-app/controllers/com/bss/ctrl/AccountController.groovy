package com.bss.ctrl

import java.awt.geom.Arc2D.Double;

import com.bss.domain.Expenses;
import com.bss.domain.User;
import com.bss.domain.UserAccounts;

class AccountController {

    def index() { 
		
		render(view:"index.gsp")
		
	}
	
	def addAccount() {
		
		String userId = session["userId"];
		User user = User.find {id == userId}
		UserAccounts account = new UserAccounts(user:user)
//		account.setBelongsTo([user:user])
		account.setAccountName(params['accountName'])
		account.setAccountType(params['accountType'])
		String currentBalance = params['currentBalance']
		double bal = currentBalance.toDouble()
		account.setCurrentBalance(bal)
		account.setEffectiveFromDate(new Date())
		account.setRemarks("Opening Balance")
		
		String msg = "", flag="0"
		
		account.validate()
		if (account.hasErrors()) {
			account.errors.allErrors.each {
				msg = 'Account not created due to ' + it
			}
		}
		
		boolean res = account.save()
		
		if (res){
			msg = "Account created"
			flag="1"
		}
		
		redirect(controller:"Dashboard", params:['msg':msg,'flag':flag])
	}
	
	def getBalance(){
		
		String accountId = params.param1
		
		UserAccounts account = UserAccounts.find { id == accountId}
		
		Map<String , String> data = new HashMap<String, String>()
		
		data.put("currentBalance", account.getCurrentBalance())
		
		render(contentType: "application/json") {
			data
		}
	}
	
	def get30DayStmt(){
		String accountId = params["accountId"]
		String userId = session["userId"];
		User user = User.find {id == userId}
		UserAccounts[] accounts = UserAccounts.findAll { user.id == userId && id == accountId}
		String strFromDate = (new Date()-30).format('dd/MM/yyyy')
		Date fromDate = Date.parse("dd/MM/yyyy", strFromDate)
		Expenses[] expenses = Expenses.findAll { userAccount.id == accountId && createdDate > fromDate}
		
		render(view:"stmt.gsp", model:['expenses':expenses])
	}
	
	def getThisMonthStmt(){
		String accountId = params["accountId"]
		String userId = session["userId"];
		User user = User.find {id == userId}
		UserAccounts[] accounts = UserAccounts.findAll { user.id == userId && id == accountId}
		String strFromDate = "01/"+(new Date()).format('MM/yyyy')
		Date fromDate = Date.parse("dd/MM/yyyy", strFromDate)
		Expenses[] expenses = Expenses.findAll { userAccount.id == accountId && createdDate > fromDate}
		
		render(view:"stmt.gsp", model:['expenses':expenses])
	}
}
