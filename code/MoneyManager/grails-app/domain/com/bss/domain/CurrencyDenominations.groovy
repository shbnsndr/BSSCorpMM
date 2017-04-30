package com.bss.domain

class CurrencyDenominations {

	String currencyUnit
	int denominationUnit
	String comments
	
    static constraints = {
		comments nullable:true
    }
}
