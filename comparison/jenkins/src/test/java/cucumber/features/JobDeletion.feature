@jenkins
Feature: Job deletion on Jenkins
	As an awesome DevOps engineer
	I want to be able to delete previously created Jenkins jobs
	in order to run save some memory and keep my instance clean.

Background:
	Given an instanciated REST client
	And a running Jenkins instance

Scenario: Delete Jenkins job
	Given a previously created job named "job-rest"
	When I delete the job "job-rest"
	Then I expect it no longer exists
