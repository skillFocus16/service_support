package customer_service_support

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class RequestFormController {

    RequestFormService requestFormService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond requestFormService.list(params), model:[requestFormCount: requestFormService.count()]
    }

    def show(Long id) {
        respond requestFormService.get(id)
    }

    def create() {
        respond new RequestForm(params)
    }

    def save(RequestForm requestForm) {
        if (requestForm == null) {
            notFound()
            return
        }

        try {
            requestFormService.save(requestForm)
        } catch (ValidationException e) {
            respond requestForm.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'requestForm.label', default: 'RequestForm'), requestForm.id])
                redirect requestForm
            }
            '*' { respond requestForm, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond requestFormService.get(id)
    }

    def update(RequestForm requestForm) {
        if (requestForm == null) {
            notFound()
            return
        }

        try {
            requestFormService.save(requestForm)
        } catch (ValidationException e) {
            respond requestForm.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'requestForm.label', default: 'RequestForm'), requestForm.id])
                redirect requestForm
            }
            '*'{ respond requestForm, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        requestFormService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'requestForm.label', default: 'RequestForm'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'requestForm.label', default: 'RequestForm'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
