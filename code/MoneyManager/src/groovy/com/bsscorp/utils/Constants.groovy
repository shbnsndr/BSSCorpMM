package com.bsscorp.utils

import java.text.DateFormat
import java.text.SimpleDateFormat

class Constants {

	public static final String OPEN_EXCHANGE_RATE_APP_ID = "OPEN_EXCHANGE_RATE_APP_ID"
	public static final String GOOGLE_DRIVE_CREDENTIAL_STORE = "GOOGLE_DRIVE_CREDENTIAL_STORE"
	public static final String STR_DATE_FORMAT = "dd/MM/yyyy"
	public static final String STR_DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
	public static final String USD = "USD"
	public static final String INR = "INR"
	public static DateFormat DATE_FORMAT = new SimpleDateFormat(STR_DATE_FORMAT)
	public static final String OPEN_EXCHANGE_RATE_URL = "https://openexchangerates.org/api/historical/" 
	
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
