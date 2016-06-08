package de.signaliduna.showcase.testpyramide

import spock.lang.Specification;
import sun.security.mscapi.PRNG;

class HelloWorldTest extends Specification{

  def helloWorld
  
  def setup() {
    helloWorld = new HelloWorld()
  }

  def "simple hello world string is printed"() {

    when:
    def printHelloWorld = helloWorld.printHelloWorld()

    then:
    printHelloWorld.equals("Hello World")
  }
}
