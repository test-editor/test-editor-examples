package org.testeditor.demo.rest.api;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting {

	private long id;

	@Length(max = 3)
	private String content;

	public Greeting() {
		// Jackson deserialization
	}

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	@JsonProperty
	public long getId() {
		return id;
	}

	@JsonProperty
	public String getContent() {
		return content;
	}

}
