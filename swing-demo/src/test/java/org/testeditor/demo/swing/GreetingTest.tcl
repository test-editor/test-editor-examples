package org.testeditor.demo.swing

# GreetingTest implements GreetingSpec
 
* Start the famous greetings application. 
 
    Component: GreetingApplication
    - Start application "org.testeditor.demo.swing.GreetingApplication"
 
* Send greetings "Hello World" to the world.
 
    Component: GreetingApplication
    - Insert "Hello World" into field <Input> 
    - Click on <GreetButton>
    - foo = Read text from <Output>
    - assert foo == "Hello World"
 
* Stop the famous greeting application.

	Component: GreetingApplication
	- Stop application