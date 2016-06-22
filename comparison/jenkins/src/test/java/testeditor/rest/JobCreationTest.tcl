package testeditor.rest

# JobCreationTest implements JobCreation

* When I pass Jenkins Job name "job-rest" and a job config file

	Component: JenkinsClient
	- Create job "job-rest" with config "xml/config.xml"

* Then I expect that Job "job-rest" exists on server

	Component: JenkinsClient
	- exists = Check that job "demo-job" exists
	- assert exists
