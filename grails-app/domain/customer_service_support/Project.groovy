package customer_service_support

class Project {

    int projectId
    String projectName
    Date createdAt
    Date dueDate
    static belongsTo = [client:Client]
    static hasMany = [tasks:Task, requestForms:RequestForm]

    static constraints = {
        projectId unique: true
        projectName size: 3..20, blank: false
        createdAt blank: false
        dueDate blank:false
    }

    String toString(){
        projectName
    }
}
