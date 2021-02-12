package customer_service_support

class Project {

    int projectId
    String projectName
    Date createdAt
    static belongsTo = [client:Client]

    static constraints = {
        projectId unique: true
        projectName size: 3..20, blank: false
        createdAt blank: false
    }

    String toString(){
        projectName
    }
}
