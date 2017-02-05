package com.bss.domain

class UserAccounts {

	String accountName
	String accountType
	double currentBalance
	Date effectiveFromDate
	Date effectiveToDate
	String remarks
	
	
    static constraints = {
		effectiveToDate nullable:true 
    }
	
	static belongsTo = [user:User]
	
	static hasMany = [expense:Expenses]
}
