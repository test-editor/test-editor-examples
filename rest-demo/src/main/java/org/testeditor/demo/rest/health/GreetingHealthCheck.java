package org.testeditor.demo.rest.health;

import com.codahale.metrics.health.HealthCheck;

public class GreetingHealthCheck extends HealthCheck {

	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}

}
