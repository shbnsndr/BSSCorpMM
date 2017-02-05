class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		
		"/$controller/getBalance/$param1?(.$format)"{
			action = [GET:"getBalance"]
		}

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
