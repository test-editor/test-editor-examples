package fixture.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testeditor.fixture.core.interaction.FixtureMethod;

import com.google.common.io.Resources;
import com.offbytwo.jenkins.JenkinsServer;

public class JenkinsClient {

	private static Logger logger = LoggerFactory.getLogger(JenkinsClient.class);

	private String url;
	private JenkinsServer server;

	public JenkinsClient() {
		try {
			String port = System.getProperty("JENKINS_PORT", "60080");
			url = "http://localhost:" + port + "/";
			URI uri = new URI(url);
			logger.info("Using uri='{}' to contact Jenkins.", uri);
			server = new JenkinsServer(uri, "admin",
					"3c6645a45456c250d26581cc1c52d22b");
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getUrl() {
		return url;
	}

	@FixtureMethod
	public void createJob(String name, String fileName) throws IOException {
		logger.info("Creating job with name='{}' from configFile='{}'.", name, fileName);
		if (checkIfJobExists(name)) {
			logger.warn("Job with name='{}' already exists, deleting job.", name);
			deleteJob(name);
		}
		server.createJob(name, getJobConfig(fileName));
	}

	@FixtureMethod
	public void deleteJob(String name) throws IOException {
		logger.info("Deleting job with name='{}'.", name);
		server.deleteJob(name);
	}

	@FixtureMethod
	public boolean checkIfJobExists(String name) throws IOException {
		logger.info("Checking if job with name='{}' exists.", name);
		boolean result = server.getJob(name) != null;
		logger.info("Job with name='{}' {}", name, result ? "exists." : "does not exist.");
		return result;
	}

	private String getJobConfig(String filename) throws IOException {
		URL url = Resources.getResource(filename);
		return Resources.toString(url, StandardCharsets.UTF_8);
	}

}
