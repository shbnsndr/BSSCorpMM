package com.bss.domain

class VehicleMileage {

	
	Date infliatedDate
	double pricePerLitre
	double litresPurchased
	double cost
	double kmBeforeInflation
	double kmAfterInflation
	double kmRun
	double millageAchieved
	
    static constraints = {
		kmAfterInflation nullable:true
		kmRun nullable:true
		millageAchieved nullable:true
    }
	
	static belongsTo = [vehicle:Vehicle]
}
