package features;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.testeditor.example.jenkins.JenkinsClient;

import cucumber.api.java8.En;

public class JenkinsClientStepDefs implements En {

	private JenkinsClient jenkinsClient;
	private StepLogic steplogic;

	private String jobName;

	@cucumber.api.java.After
	public void After() {
		try {
			jenkinsClient.deleteJob(jobName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public JenkinsClientStepDefs() throws Exception {
		steplogic = new StepLogic();

		Given("^A running jenkins instance \"([^\"]*)\"$", (String arg1) -> {
			boolean checkUrlAvailibility = steplogic.checkUrlAvailibility(arg1);
			assertTrue(checkUrlAvailibility);
		});

		Given("^an instanciated jenkins-client$", () -> {
			try {
				jenkinsClient = new JenkinsClient();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			assertNotNull(jenkinsClient);
		});

		When("^I pass Jenkins Job name \"([^\"]*)\" and a job config file$", (String arg1) -> {
			jobName = arg1;
			try {
				jenkinsClient.createJob(arg1, "xml/config.xml");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		Then("^I expect that Job \"([^\"]*)\" exists on server$", (String arg1) -> {
			try {
				boolean checkIfJobExists = jenkinsClient.checkIfJobExists(arg1);
				assertTrue(checkIfJobExists);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

}
