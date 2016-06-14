package org.testeditor.demo.swing

# GreetingTest implements GreetingSpec
 
* Start the famous greetings application. 
 
    Mask: GreetingApplication
    - Start application "org.testeditor.demo.swing.GreetingApplication"
 
* Send greetings "Hello World" to the world.
 
    Mask: GreetingApplication
    - Insert "Hello World" into field <Input> 
    - Click on <GreetButton>
    - Wait "2000" ms
    - foo = Read text from <Output>
    - assert foo == "Hello World"
 
* Stop the famous greeting application.

	Mask: GreetingApplication
	- Stop application