package org.testeditor.demo.rest.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.testeditor.demo.rest.api.Greeting;

import com.codahale.metrics.annotation.Timed;

@Path("/greeting")
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

	private final AtomicLong counter = new AtomicLong();

	@GET
	@Timed
	public Greeting sayHello(@QueryParam("name") String name) {
		final String value = "Hello, " + name + "!";
		return new Greeting(counter.incrementAndGet(), value);
	}

}
