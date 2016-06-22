package cucumber.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import cucumber.api.java8.En;
import fixture.rest.JenkinsClient;

public class JenkinsStepDefs implements En {

	private JenkinsClient jenkinsClient;
	private StepLogic steplogic;

	private String jobName;

	public JenkinsStepDefs() throws Exception {
		steplogic = new StepLogic();

		Given("^an instanciated REST client$", () -> {
			try {
				jenkinsClient = new JenkinsClient();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			assertNotNull(jenkinsClient);
		});

		Given("^a running Jenkins instance$", () -> {
			boolean checkUrlAvailibility = steplogic.checkUrlAvailibility(jenkinsClient.getUrl());
			assertTrue(checkUrlAvailibility);
		});

		Given("^a previously created job named \"([^\"]*)\"$", (String arg1) -> {
			jobName = arg1;
			try {
				jenkinsClient.createJob(arg1, "xml/config.xml");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		When("^I pass Jenkins Job name \"([^\"]*)\" and a job config file$", (String arg1) -> {
			jobName = arg1;
			try {
				jenkinsClient.createJob(arg1, "xml/config.xml");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		When("^I delete the job \"([^\"]*)\"$", (String arg1) -> {
			try {
				jobName = arg1;
				jenkinsClient.deleteJob(arg1);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		Then("^I expect it no longer exists$", () -> {
			try {
				boolean jobExists = jenkinsClient.checkIfJobExists(jobName);
				assertFalse(jobExists);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

		Then("^I expect that Job \"([^\"]*)\" exists on server$", (String arg1) -> {
			try {
				boolean jobExists = jenkinsClient.checkIfJobExists(arg1);
				assertTrue(jobExists);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

}
