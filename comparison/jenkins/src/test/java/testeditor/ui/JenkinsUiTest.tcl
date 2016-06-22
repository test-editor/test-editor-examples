package testeditor.ui

# JenkinsUiTest

* Login to Jenkins

	Mask: LoginPage
	- Open url "/login"
	- Enter "admin" into field <User>
	- Enter "NotReallyASecret" into field <Password>
	- Click on <Login>
	
* Create a ^new job with name "demoJob"

	Mask: CreateJobPage
	- Open url "/newJob"
	- Enter "demoJob" into field <Name>
	- Select item <FreeStyleProject>
	
	Mask: ConfigureJobPage
	- Click on <Save>

* Delete the job with name "demoJob"

	Mask: JobOverviewPage
	- Open url "/job/demoJob"
	- Click on <Delete>
	- Confirm alert
