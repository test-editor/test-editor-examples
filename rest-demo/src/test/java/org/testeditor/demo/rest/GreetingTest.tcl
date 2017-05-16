package org.testeditor.demo.rest

# GreetingTest

config GreetingSetup

* Start

	Component: GreetingApp
	- request = Create request to <SayHello>
	- Set query parameter "name" to "Peter" for @request
	- response = Send request @request
	- result = Get body of @response
	- assert result.content = "Hello, Peter!"