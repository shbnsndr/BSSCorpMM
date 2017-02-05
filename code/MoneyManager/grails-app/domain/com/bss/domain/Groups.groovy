package com.bss.domain

class Groups {
	
	String groupName
	Date createdDate
	String createdBy
	

    static constraints = {
    }
	
	static hasmany = [userGroup:UserGroup]
}
