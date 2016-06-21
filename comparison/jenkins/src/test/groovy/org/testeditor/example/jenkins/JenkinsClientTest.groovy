package org.testeditor.example.jenkins

import spock.lang.Specification
import spock.lang.Timeout

class JenkinsClientTest extends Specification {

    JenkinsClient jenkinsClient
    String jobName = "demo-spock"

    def setup() {
        setup: "Create new Jenkins client"
        jenkinsClient = new JenkinsClient()
    }

    @Timeout(2000)
    def cleanup() {
        cleanup:
        jenkinsClient.deleteJob(jobName)
    }

    @Timeout(2000)
    def "Create new jenkins demo job on server"() {
        when: "Creating a job using the REST API"
        jenkinsClient.createJob(jobName, "xml/config.xml")

        then: "Expect that no exception is thrown"
        noExceptionThrown()
    }

    @Timeout(2000)
    def "Check if created job exists"() {
        given: "A previously created job"
        jenkinsClient.createJob(jobName, "xml/config.xml")

        when: "Checking if the job exists"
        def jobExists = jenkinsClient.checkIfJobExists(jobName)

        then: "Expect that it exists"
        assert jobExists
    }

}
