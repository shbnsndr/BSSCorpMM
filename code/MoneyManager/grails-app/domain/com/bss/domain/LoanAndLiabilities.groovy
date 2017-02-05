package com.bss.domain

import java.util.Date;

class LoanAndLiabilities {

	Date date
	String type
	String description
	String fromToPerson
	double amount
	Date plannedReturnDate
	Date actualReturnDate
	
    static constraints = {
		actualReturnDate nullable:true 
    }
	
	static belongsTo = [user:User]
}
