package org.testeditor.demo.rest.api;

import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class Team {

	@NotNull
	private String name;
	private List<Person> members = new LinkedList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Person> getMembers() {
		return members;
	}

	public void setMembers(List<Person> members) {
		this.members = members;
	}

}
