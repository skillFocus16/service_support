package customer_service_support

class Client {

    int clientId
    String firstName
    String lastName
    String phoneNo
    String userName;
    String password
    Date createdAt

    static hasMany = [projects:Project,tasks:Task,requestForms:RequestForm]

    static constraints = {
        clientId unique: true
        firstName size: 3..20, blank:false
        lastName size: 3..20, blank:false
        phoneNo blank: false
        userName size: 3..10, blank:false
        password size: 3..15, blank:false
        password(password: true)
        createdAt blank: false
    }

    String toString() {
        firstName
    }
}
