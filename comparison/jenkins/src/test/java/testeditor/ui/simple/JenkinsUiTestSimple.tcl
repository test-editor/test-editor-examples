package testeditor.ui.simple

# JenkinsUiTestSimple

* Login to Jenkins

	Component: WebPage
	- Open "/login"
	- By "name" "j_username" send keys "admin"
	- By "name" "j_password" click
	- By "id" "yui-gen1-button" click
	
* Create job "demoJob"

	Component: WebPage
	- Open "/newJob"
	- By "id" "name" send keys "demoJob"
	- By "xpath" "//input[@value='hudson.model.FreeStyleProject']" click
	- By "id" "ok-button" click
	- By "xpath" "//span[@name='Submit']//button[1]" click
	
* Delete job "demoJob"

	Component: WebPage
	- Open "/job/demoJob"
	- By "xpath" "//a[contains(@onclick,'doDelete')]" click
	- Accept alert
