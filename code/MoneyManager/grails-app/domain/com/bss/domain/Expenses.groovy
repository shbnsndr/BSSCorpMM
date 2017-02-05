package com.bss.domain

class Expenses {

	String remarks
	double amount
	String txnType
	String createdBy
	Date createdDate
	
    static constraints = {
    }
	
	static belongsTo = [user:User, userAccount:UserAccounts]
}
