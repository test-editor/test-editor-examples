package selenide;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.WebDriverRunner.addListener;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

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
import org.testeditor.fixture.core.interaction.FixtureMethod;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class WebFixture {

	private static WebDriver driver;

	public WebFixture() {
		init();
	}

	private void init() {
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

	public void dispose() {
		closeWebDriver();
	}

	@FixtureMethod
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	@FixtureMethod
	public void open(String url) {
		Selenide.open(url);
	}
	
	@FixtureMethod
	public void sendKeys(String text, String locator, LocatorStrategy locatorStrategy) {
		SelenideElement element = getElement(locator, locatorStrategy);
		element.sendKeys(text);
	}

	@FixtureMethod
	public void click(String locator, LocatorStrategy locatorStrategy) {
		SelenideElement element = getElement(locator, locatorStrategy);
		element.click();
	}

	private SelenideElement getElement(String locator, LocatorStrategy locatorStrategy) {
		switch (locatorStrategy) {
		case ID:
			return $(By.id(locator));
		case NAME:
			return $(By.name(locator));
		case XPATH:
			return $(By.xpath(locator));
		default:
			throw new IllegalArgumentException("Unknown locatorStrategy: " + locatorStrategy);
		}
	}

}
