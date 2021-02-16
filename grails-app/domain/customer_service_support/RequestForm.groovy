package customer_service_support

class RequestForm {

    String ticketID
    String title
    String description
    String comments
    Date createdAt
    static belongsTo = [client:Client, project:Project]

    static constraints = {
        ticketID unique: true
        title size: 3..20, blank: false
        description blank: false
        comments blank: false //optional
        createdAt blank: false
    }
}
