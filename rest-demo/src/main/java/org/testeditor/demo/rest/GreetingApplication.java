package org.testeditor.demo.rest;

import org.testeditor.demo.rest.health.GreetingHealthCheck;
import org.testeditor.demo.rest.resources.GreetingResource;

import com.google.common.annotations.VisibleForTesting;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class GreetingApplication extends Application<GreetingConfiguration> {

	public static void main(String[] args) throws Exception {
		new GreetingApplication().run(args);
	}

	private Environment environment;

	@Override
	public void run(GreetingConfiguration configuration, Environment environment) throws Exception {
		environment.healthChecks().register("dummy", new GreetingHealthCheck());
		environment.jersey().register(GreetingResource.class);
		this.environment = environment;
	}

	@VisibleForTesting
	public void stop() throws Exception {
		environment.getApplicationContext().getServer().stop();
	}

}
