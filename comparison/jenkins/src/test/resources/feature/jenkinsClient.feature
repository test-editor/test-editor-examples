#Author: christian.wiedehoeft@signal-iduna.de
#Keywords Summary : jenkins rest client
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios 
#<> (placeholder)
#""
## (Comments)

#Sample Feature Definition Template
@jenkins
Feature: Create Jenkins-REST-Client
	I want to have a Jenkins-Client which can create a job on server. It should give an answer when the job exists after creation. 
	I also want to delete the job after creation.
	
	Rules:
	* A job can only be created, when it does not exist

Background:
Given A running jenkins instance "http://localhost:8080"
      And an instanciated jenkins-client

@jenkins1
Scenario: Create Jenkins job
When I pass Jenkins Job name "Demo-1" and a job config file
Then I expect that Job "Demo-1" exists on server






