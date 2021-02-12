package customer_service_support

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RequestFormServiceSpec extends Specification {

    RequestFormService requestFormService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new RequestForm(...).save(flush: true, failOnError: true)
        //new RequestForm(...).save(flush: true, failOnError: true)
        //RequestForm requestForm = new RequestForm(...).save(flush: true, failOnError: true)
        //new RequestForm(...).save(flush: true, failOnError: true)
        //new RequestForm(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //requestForm.id
    }

    void "test get"() {
        setupData()

        expect:
        requestFormService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<RequestForm> requestFormList = requestFormService.list(max: 2, offset: 2)

        then:
        requestFormList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        requestFormService.count() == 5
    }

    void "test delete"() {
        Long requestFormId = setupData()

        expect:
        requestFormService.count() == 5

        when:
        requestFormService.delete(requestFormId)
        sessionFactory.currentSession.flush()

        then:
        requestFormService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        RequestForm requestForm = new RequestForm()
        requestFormService.save(requestForm)

        then:
        requestForm.id != null
    }
}
