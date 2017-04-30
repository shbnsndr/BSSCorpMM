package com.bss.domain

class CurrencyUnits {
	
	String currencyUnit
	String currencyDesc

    static constraints = {
		currencyUnit unique:true 
    }
}
