package testeditor.ui;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.testeditor.fixture.core.interaction.FixtureMethod;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import fixture.web.CommonWebFixture;

public class WebFixture extends CommonWebFixture {

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
