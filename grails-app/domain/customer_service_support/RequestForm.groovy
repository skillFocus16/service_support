package customer_service_support

class RequestForm {

    String requestFormId
    String title
    String description
    String comments
    Date createdAt
    static belongsTo = [client:Client, project:Project]

    static constraints = {
        requestFormId unique: true
        title size: 3..20, blank: false
        description blank: false
        comments blank: true //optional
        createdAt blank: false
    }
}
