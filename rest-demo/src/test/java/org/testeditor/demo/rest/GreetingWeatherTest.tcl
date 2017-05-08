package org.testeditor.demo.rest

# GreetingWeatherTest

config GreetingSetup

* Start

	Component: GreetingApp
	// given
	- request = Create request to <AskAboutTheWeather>
	- json = Read json from "sample-request.json"
	- Set body of @request to @json

	// when
	- response = Send request @request

	// then
	- result = Get body of @response
	- assert result.content = "Hi, Peter! How is the weather in Neverland?"