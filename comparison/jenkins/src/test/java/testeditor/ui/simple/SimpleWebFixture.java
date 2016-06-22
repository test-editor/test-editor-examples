package testeditor.ui.simple;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.testeditor.fixture.core.interaction.FixtureMethod;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import fixture.web.CommonWebFixture;

public class SimpleWebFixture extends CommonWebFixture {

	@FixtureMethod
	public void open(String url) {
		Selenide.open(url);
	}

	@FixtureMethod
	public void sendKeys(String text, String locator, String locatorStrategy) {
		SelenideElement element = getElement(locator, locatorStrategy);
		element.sendKeys(text);
	}

	@FixtureMethod
	public void click(String locator, String locatorStrategy) {
		SelenideElement element = getElement(locator, locatorStrategy);
		element.click();
	}

	private SelenideElement getElement(String locator, String locatorStrategy) {
		switch (locatorStrategy) {
		case "id":
			return $(By.id(locator));
		case "name":
			return $(By.name(locator));
		case "xpath":
			return $(By.xpath(locator));
		default:
			throw new IllegalArgumentException("Unknown locatorStrategy: " + locatorStrategy);
		}
	}

}
