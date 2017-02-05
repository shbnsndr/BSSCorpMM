package com.bss.domain

class Family {

	String familyName
	String createdBy
	Date createdDate
	
    static constraints = {
    }
	
	static hasMany = [user:User]
}
