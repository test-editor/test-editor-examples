package org.testeditor.demo.rest

# GreetTeamWithModificationsTest

config GreetingSetup

* Start

	Component: GreetingApp
	// given
	- request = Create request to <GreetTeam>
	- json = Read json from "example-team.json"
	- json.name = "Hero Team"
	- json.members[1].firstName = "Maximus" 
	- Set body of @request to @json

	// when
	- response = Send request @request

	// then
	- result = Get body of @response
	- assert result.content = "Hello team Hero Team. Warm regards to Peter, Maximus!"