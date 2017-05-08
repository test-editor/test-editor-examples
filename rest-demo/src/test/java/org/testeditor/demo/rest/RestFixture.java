package org.testeditor.demo.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testeditor.fixture.core.interaction.FixtureMethod;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class RestFixture {

	private static final Logger logger = LoggerFactory.getLogger(RestFixture.class);
	
	private String baseUrl;
	private Charset charset = StandardCharsets.UTF_8;
	private Gson gson = new Gson();

	@FixtureMethod
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@FixtureMethod
	public HttpRequest createRequest(String locator, HttpMethod locatorStrategy) {
		switch (locatorStrategy) {
		case GET:
		case HEAD:
			return new GetRequest(locatorStrategy, baseUrl + locator);
		default:
			return new HttpRequestWithBody(locatorStrategy, baseUrl + locator);
		}
	}

	@FixtureMethod
	public void queryString(HttpRequest request, String name, String value) {
		request.queryString(name, value);
	}

	@FixtureMethod
	public void setBody(HttpRequest request, JsonElement jsonElement) {
		if (request instanceof HttpRequestWithBody) {
			String json = gson.toJson(jsonElement);
			((HttpRequestWithBody) request).body(json);
			request.header("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
		} else {
			throw new IllegalArgumentException("Cannot set body on request: " + request.getClass().getSimpleName());
		}
	}

	@FixtureMethod
	public HttpResponse<String> sendRequest(HttpRequest request) throws UnirestException {
		return request.asString();
	}

	@FixtureMethod
	public long getStatus(HttpResponse<String> response) {
		return response.getStatus();
	}
	
	@FixtureMethod
	public JsonElement getBody(HttpResponse<String> response) {
		return new JsonParser().parse(response.getBody()).getAsJsonObject();
	}

	@FixtureMethod
	public JsonElement readJson(String fileName) throws IOException {
		URL resource = Resources.getResource(fileName);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream(), charset))) {
			return new JsonParser().parse(reader).getAsJsonObject();
		}
	}

	@FixtureMethod
	public void logJson(JsonElement element) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(element);
		logger.info("Json is:\n{}", json);
	}

}
