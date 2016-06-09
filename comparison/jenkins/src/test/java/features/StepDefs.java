package features;

import cucumber.api.java8.En;
import de.signaliduna.showcase.testpyramide.HelloWorld;

import static org.junit.Assert.assertEquals;

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
