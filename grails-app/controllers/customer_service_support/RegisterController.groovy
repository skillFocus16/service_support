package customer_service_support

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional
@Secured('permitAll')
class RegisterController {

    static allowedMethods = [register: "POST"]

    def index() { }

    def register() {
        if(!params.password.equals(params.repassword)) {
            flash.message = "Password and Re-Password not match"
            redirect action: "index"
            return
        } else {
            try {
                def user = User.findByUsername(params.username)
                if(!user){
                    user=new User(username: params.username, password: params.password, fullname: params.fullname)
                    user.save(failOnError:true)
                }

               def role = Role.get(params.role.id)
                //def role = Role.findByName('ROLE_USER')
                println "user "+user
                println "user "+role
                if(user && role) {
                      UserRole.create user, role
                      //UserRole.create user, role,true

                    UserRole.withSession {
                        it.flush()
                        it.clear()
                    }

                    flash.message = "You have registered successfully. Please login."
                    redirect controller: "login", action: "auth"
                } else {
                    flash.message = "Register failed"
                    render view: "index"
                    return
                }
            } catch (ValidationException e) {
                flash.message = "Register Failed "+e
                redirect action: "index"
                return
            }
        }
    }
}