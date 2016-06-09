package features;

import cucumber.api.java8.En;
import de.signaliduna.showcase.testpyramide.jenkinsClient.JenkinsClient;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.*;

public class JenkinsClientStepDefs implements En {

    private JenkinsClient jenkinsClient;
    private StepLogic steplogic;

    private String jobName;

    @cucumber.api.java.After
    public void After() {
        String status = jenkinsClient.deleteJob(jobName);
        assertEquals("200", status);
    }

    public JenkinsClientStepDefs() {
        steplogic = new StepLogic();

        Given("^A running jenkins instance \"([^\"]*)\"$", (String arg1) -> {
            boolean checkUrlAvailibility = steplogic.checkUrlAvailibility(arg1);
            assertTrue(checkUrlAvailibility);
        });

        Given("^an instanciated jenkins-client$", () -> {
            jenkinsClient = new JenkinsClient();
            assertNotNull(jenkinsClient);
        });

        When("^I pass Jenkins Job name \"([^\"]*)\" and a job config file$", (String arg1) -> {
            jobName = arg1;
            String status = jenkinsClient.createJob(arg1, loadedJobConfigFile());
            assertEquals("200", status);
        });

        Then("^I expect that Job \"([^\"]*)\" exists on server$", (String arg1) -> {
            boolean checkIfJobExists = jenkinsClient.checkIfJobExists(arg1);
            assertTrue(checkIfJobExists);
        });
    }

    private File loadedJobConfigFile() {
        URL resource = JenkinsClientStepDefs.class.getClassLoader().getResource("xml/config.xml");
        File file = null;
        try {
            file = new File(resource.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file;
    }
}
