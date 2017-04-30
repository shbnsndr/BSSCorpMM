package com.bss.domain

class Properties {
	
	String propertyKey
	String propertyValue

    static constraints = {
		propertyKey unique:true
    }
	
	public static String getValue(String propKey){
		Properties prop = Properties.find{ propertyKey == propKey}
		return prop.propertyValue
	}
}
