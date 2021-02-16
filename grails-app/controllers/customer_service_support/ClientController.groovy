package customer_service_support

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ClientController {
    ClientService clientService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond clientService.list(params), model:[clientCount: clientService.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond clientService.get(id)
    }

    @Secured('ROLE_ADMIN')
    def create() {
        respond new Client(params)
    }

    @Secured('ROLE_ADMIN')
    def save(Client client) {
        if (client == null) {
            notFound()
            return
        }

        try {
            clientService.save(client)
        } catch (ValidationException e) {
            respond client.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'client.label', default: 'Client'), client.id])
                redirect client
            }
            '*' { respond client, [status: CREATED] }
        }
    }

    @Secured('ROLE_ADMIN')
    def edit(Long id) {
        respond clientService.get(id)
    }

    @Secured('ROLE_ADMIN')
    def update(Client client) {
        if (client == null) {
            notFound()
            return
        }

        try {
            clientService.save(client)
        } catch (ValidationException e) {
            respond client.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'client.label', default: 'Client'), client.id])
                redirect client
            }
            '*'{ respond client, [status: OK] }
        }
    }

    @Secured('ROLE_ADMIN')
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        clientService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'client.label', default: 'Client'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'client.label', default: 'Client'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

/*    def login={}

    def authenticate = {
        def user = Client.findByUserNameAndPassword(params.userName,params.password)
        if(user){
            session.user = user
            flash.message = "Hello ${user.firstName}!"
            redirect(action: "login")
        }else {
            flash.message = "Sorry, ${user.firstName}. Please try again"
            redirect(action: "login")
        }
    }

    def logout = {
        flash.message = "Thank you ${session.user.firstName}!"
        session.user =null
        redirect(action: "login")

    }*/
}
