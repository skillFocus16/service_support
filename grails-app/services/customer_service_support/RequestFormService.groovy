package customer_service_support

import grails.gorm.services.Service

@Service(RequestForm)
interface RequestFormService {

    RequestForm get(Serializable id)

    List<RequestForm> list(Map args)

    Long count()

    void delete(Serializable id)

    RequestForm save(RequestForm requestForm)

}