package com.bss.domain

import org.hibernate.hql.internal.ast.tree.FromClause;

import com.bsscorp.utils.AppUtils;
import com.bsscorp.utils.Constants;

import groovy.json.JsonSlurper

class ForexExchangeRates {
	
	Date date
	String fromCurrency
	String toCurrency
	double ratePerUnit

    static constraints = {
    }
	
	public static double getForexRate(Date givenDate, String from, String to){
		
		double ratePerUnit=0;
		ForexExchangeRates[] testRates = ForexExchangeRates.findAll { date == givenDate && fromCurrency == from && toCurrency == to }
		ForexExchangeRates rate = ForexExchangeRates.find { date == givenDate && fromCurrency == from && toCurrency == to }
		
		if(rate != null){
			return rate.ratePerUnit
		}
		
		if(from != Constants.USD && to == Constants.USD){
			rate = ForexExchangeRates.find { date == givenDate && fromCurrency == to && toCurrency == from }
			
			if(rate!=null){
				double calculatedRate = 1 / rate.ratePerUnit
				
				rate = new ForexExchangeRates();
				rate.setDate(givenDate)
				rate.setFromCurrency(from)
				rate.setToCurrency(to)
				rate.setRatePerUnit(calculatedRate.round(5))
				
				rate.save()
				
				return calculatedRate.round(5)
			}
		}
		
		ForexExchangeRates fromUsdRate = ForexExchangeRates.find { date == givenDate && fromCurrency == Constants.USD && toCurrency == from }
		ForexExchangeRates toUsdRate = ForexExchangeRates.find { date == givenDate && fromCurrency == Constants.USD && toCurrency == to }
		
		if(fromUsdRate != null && toUsdRate != null){
			double calculatedRate = toUsdRate.ratePerUnit/fromUsdRate.ratePerUnit
			
			rate = new ForexExchangeRates();
			rate.setDate(givenDate)
			rate.setFromCurrency(from)
			rate.setToCurrency(to)
			rate.setRatePerUnit(calculatedRate.round(5))
			
			rate.save()
			
			return calculatedRate.round(5)
		}

		String response = AppUtils.getExchangeRate(Properties.getValue(Constants.OPEN_EXCHANGE_RATE_APP_ID), givenDate)
		
		def responseJson = new JsonSlurper().parseText(response)
		
		double fromValue = responseJson.rates.get(from)
		double toValue = responseJson.rates.get(to)
		
		double calculatedRate = toValue/fromValue;
		
		
		rate = new ForexExchangeRates();
		rate.setDate(givenDate)
		rate.setFromCurrency(from)
		rate.setToCurrency(to)
		rate.setRatePerUnit(calculatedRate.round(5))
		
		rate.save()
		
		
		//Out of scope for now
		/*def task = new TimerTask() {
			
			  void run() {
				println "task running at ${new Date()}"
				def currRates = responseJson.rates
				def currKeys = currRates.keySet()
				
				for(String currKey : currKeys){
					if(currKey != Constants.USD){
						
						ForexExchangeRates[] checkRates = ForexExchangeRates.findAll { date == givenDate && fromCurrency == Constants.USD && toCurrency == currKey }
						
						if(checkRates == null || checkRates.length == 0){
							rate = new ForexExchangeRates();
							rate.setDate(givenDate)
							rate.setFromCurrency(Constants.USD)
							rate.setToCurrency(currKey)
							rate.setRatePerUnit(currRates.get(currKey))
							rate.save()
						}
						
						checkRates = ForexExchangeRates.findAll { date == givenDate && fromCurrency == currKey && toCurrency == Constants.USD }
						
						if(checkRates == null || checkRates.length == 0){
							rate = new ForexExchangeRates();
							rate.setDate(givenDate)
							rate.setFromCurrency(currKey)
							rate.setToCurrency(Constants.USD)
							double viseVersaRate = 1/currRates.get(currKey)
							rate.setRatePerUnit(viseVersaRate.round(5))
							rate.save()
						}
						
					}
					
				}
				
			  }
			}
			
			def firstExecutionDelay = 2000
			
			new Timer(true).schedule(task, firstExecutionDelay)*/
		
		return calculatedRate.round(5)
	}
}
