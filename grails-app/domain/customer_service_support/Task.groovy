package customer_service_support

class Task {

    String taskId
    String taskName
    String description
    Date dueDate
    static belongsTo = [client:Client, project:Project]

    static constraints = {
        taskId unique: true
        taskName size: 3..30, blank: false
        description size: 3..20, blank: false
        dueDate blank:false
    }
}
