package com.bss.ctrl

import com.bss.domain.User
import com.bss.domain.Vehicle;
import com.bss.domain.VehicleMileage;

class VehicleController {

    def index() { 
		
		render(view:"index.gsp")
	}
	
	def addVehicle(){
		
		String userId = session["userId"];
		User user = User.find {id == userId}
		Vehicle vehicle = new Vehicle(user:user)
		vehicle.setVehicleNumber(params['vehicleNumber'])
		vehicle.setEngineType(params['engineType'])
		
		String msg = "", flag="0"
		
		vehicle.validate()
		if (vehicle.hasErrors()) {
			vehicle.errors.allErrors.each {
				msg = 'Vehicle not created due to ' + it
			}
		}
		
		boolean res = vehicle.save()
		
		if (res){
			msg = "Vehicle created"
			flag="1"
		}
		
		redirect(controller:"Dashboard", params:['msg':msg,'flag':flag])
	}
	
	def getMileage(){
		
		String vehicleNumber = params['vehicle']
		Vehicle v = Vehicle.find { id == vehicleNumber }
		VehicleMileage[] vm = VehicleMileage.findAll {vehicle == v}
		
		render(view:"mileage.gsp", model:['vm':vm, 'vehicle':v])
	}
}
