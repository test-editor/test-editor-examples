package org.testeditor.demo.rest.manual;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testeditor.demo.rest.GreetingFixture;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

import io.restassured.RestAssured;

public class ManualTest {

	private GreetingFixture greetingFixture = new GreetingFixture();

	@Before
	public void setup() throws Exception {
		greetingFixture.start();
	}

	@After
	public void cleanup() throws Exception {
		greetingFixture.stop();
	}

	@Test
	public void withRestAssured() throws Exception {
		// given
		RestAssured.basePath = greetingFixture.getBaseUrl();
		given().param("name", "Mark")

		// when
		.when().get("/greeting")

		// then
		.then().body("content", equalTo("Hello, Mark!"));
	}

	@Test
	public void withUnirest() throws UnirestException {
		// given
		String greetingPath = greetingFixture.getBaseUrl() + "/greeting";
		HttpRequest request = Unirest.get(greetingPath).queryString("name", "Mark");

		// when
		HttpResponse<JsonNode> json = request.asJson();

		// then
		assertEquals(HttpStatus.SC_OK, json.getStatus());
		assertEquals("Hello, Mark!", json.getBody().getObject().get("content"));
	}
	
}
