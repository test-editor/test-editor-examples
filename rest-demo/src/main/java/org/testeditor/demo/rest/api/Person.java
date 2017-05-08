package org.testeditor.demo.rest.api;

import javax.validation.constraints.NotNull;

public class Person {

	@NotNull private String firstName;
	@NotNull private String lastName;
	@NotNull private SimpleAddress address;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public SimpleAddress getAddress() {
		return address;
	}

	public void setAddress(SimpleAddress address) {
		this.address = address;
	}

}
