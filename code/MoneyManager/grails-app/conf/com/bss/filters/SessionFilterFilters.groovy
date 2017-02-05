package com.bss.filters

class SessionFilterFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
				
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
