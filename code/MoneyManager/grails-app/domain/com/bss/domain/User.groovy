package com.bss.domain

class User {

	String name
	String loginId
	String password
	Date dob
	String gender
	String isAdmin
	Date createdDate
	String createdBy
	String driveFolderId
	
	static belongsTo = [family: Family]
	static hasmany = [userGroup:UserGroup, userAccount:UserAccounts, expenses:Expenses, vehicles:Vehicle, loanLiabilities:LoanAndLiabilities]
	
    static constraints = { }
}
