package customer_service_support

class Client {

    int clientId
    String fName
    String lName
    String phoneNo
    Date createdAt
    static hasMany = [projects:Project]

    static constraints = {
        clientId unique: true
        fName size: 3..20, blank:false
        lName size: 3..20, blank:false
        phoneNo blank: false
        createdAt blank: false
    }

    String toString() {
        fName
    }
}
