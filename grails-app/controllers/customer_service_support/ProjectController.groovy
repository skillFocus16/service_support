package customer_service_support

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProjectController {

    ProjectService projectService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN','ROLE_USER','ROLE_CLIENT'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond projectService.list(params), model:[projectCount: projectService.count()]
    }

    @Secured(['ROLE_ADMIN','ROLE_USER','ROLE_CLIENT'])
    def show(Long id) {
        respond projectService.get(id)
    }

    @Secured(['ROLE_ADMIN','ROLE_CLIENT'])
    def create() {
        respond new Project(params)
    }

    @Secured(['ROLE_ADMIN','ROLE_CLIENT'])
    def save(Project project) {
        if (project == null) {
            notFound()
            return
        }

        try {
            projectService.save(project)
        } catch (ValidationException e) {
            respond project.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'project.label', default: 'Project'), project.id])
                redirect project
            }
            '*' { respond project, [status: CREATED] }
        }
    }
    @Secured(['ROLE_ADMIN','ROLE_USER','ROLE_CLIENT'])
    def edit(Long id) {
        respond projectService.get(id)
    }

    @Secured(['ROLE_ADMIN','ROLE_USER','ROLE_CLIENT'])
    def update(Project project) {
        if (project == null) {
            notFound()
            return
        }

        try {
            projectService.save(project)
        } catch (ValidationException e) {
            respond project.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'project.label', default: 'Project'), project.id])
                redirect project
            }
            '*'{ respond project, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_CLIENT'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        projectService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'project.label', default: 'Project'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /*def auth(){
        if (!session.user){
            redirect(controller:"Client", action: "login")
            return false
        }
    }*/
}
