package customer_service_support

class BootStrap {

    def init = { servletContext ->
        def roleAdmin=Role.findOrSaveWhere(authority: 'ROLE_ADMIN')
        roleAdmin.name="Admin role"
        roleAdmin.description="This is the admin of the system"
        roleAdmin.save()
        def roleUser=Role.findOrSaveWhere(authority: 'ROLE_USER',name:"User role",description:'This is the user of the system')
        def admin=User.findOrSaveWhere(username: 'admin',password:"123456",fullname: "Super Admin")
    }
    def destroy = {
    }
}
