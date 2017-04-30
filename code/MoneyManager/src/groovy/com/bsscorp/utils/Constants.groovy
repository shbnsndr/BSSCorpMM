package com.bsscorp.utils

import java.text.DateFormat
import java.text.SimpleDateFormat

class Constants {

	static final String OPEN_EXCHANGE_RATE_APP_ID = "OPEN_EXCHANGE_RATE_APP_ID"
	static final String STR_DATE_FORMAT = "dd/MM/yyyy"
	static final String STR_DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
	static final String USD = "USD"
	static final String INR = "INR"
	static DateFormat DATE_FORMAT = new SimpleDateFormat(STR_DATE_FORMAT)
	static final String OPEN_EXCHANGE_RATE_URL = "https://openexchangerates.org/api/historical/" 
	
	public static Date convertDateToString(String customFormat){
		
	}
	
	public static Date convertStringToDate(String strDate, String customFormat){
		
		Date date = null
		
		if(customFormat == null){
			date = DATE_FORMAT.parse(strDate)
		}else{
			DateFormat df = new SimpleDateFormat(customFormat)
			date = df.parse(strDate);
		}
		
		return date
	}
}
