package features;

import static org.junit.Assert.assertEquals;

import org.testeditor.example.helloworld.HelloWorld;

import cucumber.api.java8.En;

public class StepDefs implements En {
    private HelloWorld helloWorld;
    private String result;

    public StepDefs() {
        Given("^Instance of Hello World Service$", () -> {
            helloWorld = new HelloWorld();
        });

        When("^helloWord is invoked$", () -> {
            result = helloWorld.printHelloWorld();
        });

        Then("^\"([^\"]*)\" is returned$", (String arg1) -> {
            assertEquals(arg1, result);
        });
    }
}
