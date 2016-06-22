@jenkins
Feature: Job creation on Jenkins
	As an awesome DevOps engineer
	I want to be able to create Jenkins jobs
	in order to run them afterwards.

Background:
	Given an instanciated REST client
	And a running Jenkins instance
	
Scenario: Create Jenkins job
	When I pass Jenkins Job name "job-rest" and a job config file
	Then I expect that Job "job-rest" exists on server