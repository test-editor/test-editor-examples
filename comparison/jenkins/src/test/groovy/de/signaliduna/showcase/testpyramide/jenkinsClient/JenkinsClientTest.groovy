package de.signaliduna.showcase.testpyramide.jenkinsClient

import spock.lang.Specification
import spock.lang.Timeout

class JenkinsClientTest extends Specification{

  def static JenkinsClient jenkinsClient
  def jobName = "DEMO2"

  def setupSpec() {
    setup: "Create new Jenkins Client"
    jenkinsClient = new JenkinsClient()
  }

  @Timeout(2000)
  def cleanup() {
    cleanup: "Created jobs are deleted; HTTP-Code 404 is returned when job was not found"
    def status = jenkinsClient.deleteJob(jobName)
    assert status.equals("200")
  }

  @Timeout(2000)
  def "Create new jenkins demo job on server" () {

    given: "We need an example job config file"
    def file = loadedJobConfigFile()

    when: "Use REST-Remote API to create job with an example name"
    def status = jenkinsClient.createJob(jobName, file)

    then: "When Job was sucessfull created, Jenkins returns Status-Code 200"
    status.equals("200")
  }

  @Timeout(2000)
  def "Check if created job exists"() {
    given: "created jenkins job"
    def file = loadedJobConfigFile()
    def status = jenkinsClient.createJob(jobName, file)
    status.equals("200")

    when:
    status = jenkinsClient.checkIfJobExists(jobName)

    then:
    status.equals(true)
  }

  private File loadedJobConfigFile() {
    def resource = JenkinsClientTest.class.getClassLoader().getResource("xml/config.xml");
    def file = null;
    try {
      file = new File(resource.toURI());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return file
  }
}
