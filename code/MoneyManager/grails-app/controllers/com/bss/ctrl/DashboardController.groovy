package com.bss.ctrl

import com.bss.domain.LoanAndLiabilities
import com.bss.domain.User
import com.bss.domain.UserAccounts
import com.bss.domain.Vehicle

class DashboardController {

    def index() { 
		
		String usrName = session["userName"]
		String userId = session["userId"]
		
		User user = User.find {id == userId}
		UserAccounts[] accounts = UserAccounts.findAll{ user.id ==  userId && effectiveToDate == null}
		Vehicle[] vehicles = Vehicle.findAll{user.id == userId}
		LoanAndLiabilities[] loanAndLiabilities = LoanAndLiabilities.findAll{user.id == userId && actualReturnDate == null}
		
		String msg = params['msg']
		String msgFlag = params['flag']
		
		render(view:"index.gsp", model:['userAccounts':accounts, 'vehicles':vehicles, 'loanAndLiabilities':loanAndLiabilities, 'msg':msg, 'msgFlag':msgFlag])
	}

}
