package org.testeditor.demo.rest.resources;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.testeditor.demo.rest.api.Greeting;
import org.testeditor.demo.rest.api.Team;

import com.codahale.metrics.annotation.Timed;

@Path("/greeting")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

	private final AtomicLong counter = new AtomicLong();

	@GET
	@Timed
	public Greeting sayHello(@QueryParam("name") String name) {
		final String value = "Hello, " + name + "!";
		return new Greeting(counter.incrementAndGet(), value);
	}

	@POST
	@Timed
	public Greeting greetTeam(@NotNull @Valid Team team) {
		if (team.getMembers() == null || team.getMembers().isEmpty()) {
			return new Greeting(counter.incrementAndGet(), "So sad that team " + team.getName() + " has no members!");
		} else {
			String nameList = team.getMembers().stream().map(person -> person.getFirstName()).collect(Collectors.joining(", "));
			return new Greeting(counter.incrementAndGet(), "Hello team " + team.getName() + ". Warm regards to " + nameList + "!");
		}

	}

}
