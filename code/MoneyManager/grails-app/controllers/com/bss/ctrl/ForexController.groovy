package com.bss.ctrl

import com.bss.domain.CurrencyDenominations;
import com.bss.domain.CurrencyUnits;
import com.bss.domain.ForexExchangeRates;
import com.bsscorp.utils.Constants;

class ForexController {

    def index() {
		
		CurrencyUnits[] units = CurrencyUnits.list(); 
		
		render(view:"index.gsp", model:['units':units])
	}
	
	def calculator(){
		
		String strDate = params.get("strDate")
		String fromCurrency = params.get("fromCurrency")
		String toCurrency = params.get("toCurrency")
		
		String msg=""
		String flag="0"
		double exchangeRate=0
		
		if(strDate == null){
			msg = "Enter a Valid date in DD/MM/YYYY format in EDT time zone"
		}else {
			
			try{
				
				Date givenDate = Constants.convertStringToDate(strDate, null)
				Date currentDate = new Date()
				
				if(givenDate > currentDate){
					msg="Enter a Valid date in DD/MM/YYYY format in EDT time zone"
				}else if(fromCurrency == toCurrency){
					msg = "From and To Currency shouldnot be same."
				}else{
				
					exchangeRate = ForexExchangeRates.getForexRate(givenDate, fromCurrency, toCurrency)
					flag = "1"	
				}
				
			}catch(Exception e){
				msg = "Enter a Valid date in DD/MM/YYYY format in EDT time zone. From and To Currency should not be same."
			}
			
		}
		
		CurrencyDenominations[] denominations = CurrencyDenominations.findAll { currencyUnit == fromCurrency }
		
		CurrencyUnits[] units = CurrencyUnits.list();
		
		render(view:"index.gsp", model:['units':units, 'exchangeRate':exchangeRate, 'msg':msg
										, 'msgFlag':flag, 'denominations':denominations
										, 'date': strDate, 'fromCurr':fromCurrency, 'toCurr':toCurrency
										])
		
		
	}
}
