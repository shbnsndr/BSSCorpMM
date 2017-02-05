package com.bss.domain

class Vehicle {
	
	String VehicleNumber
	String engineType

    static constraints = {
		
    }
	
	static hasMany = [vehicleMileage:VehicleMileage]
	
	static belongsTo = [user:User]
}

