package root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testeditor.fixture.core.interaction.FixtureMethod;

public class MyApplicationFixture {

	private static Logger logger = LoggerFactory.getLogger(MyApplicationFixture.class);

	@FixtureMethod
	public void ReloadPage() {
		logger.info("Reload");
	}

	@FixtureMethod
	public void EnterIntoTextField(String text, String element, MyLocatorStrategy locatorStrategy) {
		logger.info("Enter '" + text + "' into element '" + element + "' using locator strategy '"
				+ locatorStrategy.name() + "'");
	}

	@FixtureMethod
	public String ReadFromTextField(String element, MyLocatorStrategy locatorStrategy) {
		logger.info("Read from element '" + element + "' using locator strategy '" + locatorStrategy.name() + "'");
		return "42";
	}

	@FixtureMethod
	public void ClearTextField(String element, MyLocatorStrategy locatorStrategy) {
		logger.info("Clear element '" + element + "' using locator strategy '" + locatorStrategy.name() + "'");
	}

	@FixtureMethod
	public void StartBrowser(String path) {
		logger.info("Starting browser from path '" + path + "'");
	}

	@FixtureMethod
	public void CloseBrowser() {
		logger.info("Closed browser");
	}

	@FixtureMethod
	public void Initialize() {
		logger.info("Initialized");
	}

	@FixtureMethod
	public void Click(String element, MyLocatorStrategy locatorStrategy) {
		logger.info("Clicked element '" + element + "' using locator strategy '" + locatorStrategy.name() + "'");
	}

	@FixtureMethod
	public void NavigateTo(String url) {
		logger.info("Navigate to '" + url + "'");
	}

}