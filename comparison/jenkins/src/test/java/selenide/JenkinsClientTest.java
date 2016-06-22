package selenide;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.junit.ScreenShooter;

public class JenkinsClientTest {

	private static String JOB_NAME = "demoJob";

	@Rule
	public ScreenShooter screenShooter = ScreenShooter.failedTests();

	private WebFixture fixture = new WebFixture();

	@After
	public void closeDriver() {
		fixture.dispose();
	}

	@Test
	public void createAndDeleteJob() throws InterruptedException {
		// Login
		open("/login");
		$(By.name("j_username")).sendKeys("admin");
		$(By.name("j_password")).sendKeys("NotReallyASecret");
		$(By.id("yui-gen1-button")).click();

		// Create job
		open("/newJob");
		$(By.id("name")).sendKeys(JOB_NAME);
		$(By.xpath("//input[@value='hudson.model.FreeStyleProject']")).click();
		$(By.id("ok-button")).click();
		$(By.xpath("//span[@name='Submit']//button[1]")).click();

		// Highlight job
		open("/");
		Highlighter.highlight(($(By.id("job_" + JOB_NAME)).shouldBe(visible)));

		// Delete job
		open("/job/" + JOB_NAME);
		$(By.xpath("//a[contains(@onclick,'doDelete')]")).click();
		fixture.acceptAlert();
	}

}
