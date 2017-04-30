package com.bss.ctrl

import com.bss.domain.User;

class LoginController {

    def index() { }
	
	def authUsr(){
		
		User user = null
		
//		String usrName = session["userName"]
//		if(usrName == null) {
//			println "User not is session"
			user = User.find { loginId == params.get("userName") && password == params.get("password") }
//		}else{
//			println "User available is session"
//			user = User.find { loginId == usrName }
//		}
		
		if(user == null){
			session.invalidate()
			flash.message = "invalid credentials"
			render(view:"../index.gsp")
		}else{
			session["userName"] = user.loginId
			session["userId"] = user.id
			session["name"] = user.name
			session["isAdmin"] = user.isAdmin
			redirect(controller:"Dashboard")
		}
		
	}
	
	def logout(){
		session.invalidate()
		render(view:"../index.gsp")
	}
	
}
