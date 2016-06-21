package testeditor

# JenkinsTest

* Create a Jenkins job with name "demo-job"

	// given
	Component: JenkinsInstance

	// when
	- Create job "demo-job" with config "xml/config.xml"
	- exists = Check that job "demo-job" exists

	// then
	- assert exists

	// cleanup
 	- Delete job "demo-job"
