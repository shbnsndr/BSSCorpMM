package com.bsscorp.utils

import com.bss.domain.Properties;
import groovy.json.JsonSlurper

class AppUtils {

	public static String getExchangeRate(String appId, Date date){
		String url = Constants.OPEN_EXCHANGE_RATE_URL+date.format(Constants.STR_DATE_FORMAT_YYYY_MM_DD)+".json?app_id="+appId
		
		def connection = new URL(url).openConnection() as HttpURLConnection
		
		String response = connection.inputStream.text
		return response
		
	}
	
	public static void main(){
		getExchangeRate(Properties.getValue(Constants.OPEN_EXCHANGE_RATE_APP_ID), "29/04/2017")
		
	}
}
