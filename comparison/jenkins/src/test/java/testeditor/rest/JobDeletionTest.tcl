package testeditor.rest

# JobDeletionTest implements JobDeletion

* Given a previously created job named "job-rest"

	Component: JenkinsClient
	- Create job "job-rest" with config "xml/config.xml"

* When I delete the job "job-rest"

Component: JenkinsClient
- Delete job "job-rest"

* Then I expect it no longer exists

	Component: JenkinsClient
	- exists = Check that job "demo-job" exists
	- assert !exists
