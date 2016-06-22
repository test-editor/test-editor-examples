package selenide;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.addListener;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.ScreenShooter;

public class JenkinsClientTest {

	@Rule
	public ScreenShooter screenShooter = ScreenShooter.failedTests();

	private static WebDriver driver;
	private static String JOB_NAME = "demoJob";

	@BeforeClass
	public static void setupDriver() {
		timeout = 5000;
		String port = System.getProperty("JENKINS_PORT", "60080");
		baseUrl = "http://localhost:" + port;
		startMaximized = false;
		addListener(new Highlighter());

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		Proxy proxy = new Proxy();
		proxy.setNoProxy("localhost:" + port);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, proxy);

		driver = new FirefoxDriver(new FirefoxBinary(), firefoxProfile, cap);
		WebDriverRunner.setWebDriver(driver);

		// dismiss any proxy auth dialog
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.dismiss();
		} catch (Exception e) {
		}
	}

	@AfterClass
	public static void closeDriver() {
		closeWebDriver();
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
		driver.switchTo().alert().accept();
	}

}
