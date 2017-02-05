package com.bss.ctrl

import com.bss.domain.User;

class LoginController {

    def index() { }
	
	def authUsr(){
		
		User user
		
		String usrName = session["userName"]
		if(usrName == null) {
			user = User.find { loginId == params.get("userName") && password.equals(params.get("password")) }
		}else{
			user = User.find { loginId == usrName }
		}
		
		if(user == null){
			session.invalidate()
			flash.message = "invalid credentials"
			render(view:"../index.gsp")
		}else{
			session["userName"] = user.loginId
			session["userId"] = user.id
			session["name"] = user.name
			redirect(controller:"Dashboard")
			//render(view:"../dashboard/index.gsp", model:[user:user])
		}
		
	}
	
	def logout(){
		session.invalidate()
		render(view:"../index.gsp")
	}
}
