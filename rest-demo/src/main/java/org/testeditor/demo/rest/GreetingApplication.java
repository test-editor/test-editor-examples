package org.testeditor.demo.rest;

import org.eclipse.jetty.server.Server;
import org.testeditor.demo.rest.health.GreetingHealthCheck;
import org.testeditor.demo.rest.resources.GreetingResource;

import com.google.common.annotations.VisibleForTesting;

import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.lifecycle.ServerLifecycleListener;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class GreetingApplication extends Application<GreetingConfiguration> {

	public static void main(String[] args) throws Exception {
		new GreetingApplication().run(args);
	}

	private Server jetty;
	private int httpPort;
	private boolean started;

	@Override
	public void initialize(Bootstrap<GreetingConfiguration> bootstrap) {
		super.initialize(bootstrap);
		bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
	}

	@Override
	public void run(GreetingConfiguration configuration, Environment environment) throws Exception {
		environment.healthChecks().register("dummy", new GreetingHealthCheck());
		environment.jersey().register(GreetingResource.class);
		environment.lifecycle().addServerLifecycleListener(new ServerLifecycleListener() {
			@Override
			public void serverStarted(Server server) {
				jetty = server;
				started = true;
				httpPort = getLocalPort(server);
			}
		});
	}

	@VisibleForTesting
	public int getHttpPort() {
		return httpPort;
	}

	@VisibleForTesting
	public boolean isStarted() {
		return started;
	}

	@VisibleForTesting
	public void stop() throws Exception {
		jetty.stop();
		started = false;
	}

}
