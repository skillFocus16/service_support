package customer_service_support

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
//        "/"(controller:'login', action:'auth')
//        "/"(controller:'client', action:'index')
//        "/"(controller:'hello', action:'index')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
