package org.testeditor.demo.rest;

import org.testeditor.fixture.core.interaction.FixtureMethod;

public class GreetingFixture {

	private Thread thread;
	private GreetingApplication application;

	@FixtureMethod
	public void start() throws Exception {
		application = new GreetingApplication();
		thread = new Thread(() -> {
			try {
				application.run("server");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		thread.start();
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

}
