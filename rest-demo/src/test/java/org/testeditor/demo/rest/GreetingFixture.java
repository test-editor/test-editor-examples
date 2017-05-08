package org.testeditor.demo.rest;

import org.junit.Assert;
import org.testeditor.fixture.core.interaction.FixtureMethod;

public class GreetingFixture {

	private Thread thread;
	private GreetingApplication application;

	@FixtureMethod
	public void start() throws Exception {
		application = new GreetingApplication();
		thread = new Thread(() -> {
			try {
				application.run("server", "config.yml");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		thread.start();
		waitForApplicationToStart();
	}

	private void waitForApplicationToStart() throws InterruptedException {
		int counter = 0;
		while (!application.isStarted() && counter++ <= 20) {
			sleep(200);
		}
		Assert.assertTrue("Application is not started.", application.isStarted());
	}

	@FixtureMethod
	public void stop() throws Exception {
		application.stop();
		application = null;
	}

	@FixtureMethod
	public void sleep(int millis) throws InterruptedException {
		Thread.sleep(millis);
	}

	@FixtureMethod
	public String getBaseUrl() {
		return "http://localhost:" + application.getHttpPort();
	}
	
}
