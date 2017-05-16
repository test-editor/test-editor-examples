package org.testeditor.demo.rest

# GreetTeamTest

config GreetingSetup

* Start

	Component: GreetingApp
	// given
	- request = Create request to <GreetTeam>
	- json = Read json from "example-team.json"
	- Set body of @request to @json

	// when
	- response = Send request @request

	// then
	- result = Get body of @response
	- assert result.content = "Hello team Example Team. Warm regards to Peter, Max!"