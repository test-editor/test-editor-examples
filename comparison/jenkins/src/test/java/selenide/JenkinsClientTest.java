package selenide;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.addListener;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class JenkinsClientTest {

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    private static WebDriver driver;

    @BeforeClass
    public static void openInbox() {
        timeout = 5000;
        baseUrl = "localhost:8080";
        startMaximized = false;
        addListener(new Highlighter());

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile firefoxProfile = profile.getProfile("Selenium");

        driver = new FirefoxDriver(firefoxProfile);
        WebDriverRunner.setWebDriver(driver);

    }

    @AfterClass
    public static void logout() {
        $(By.partialLinkText("DEMO-2")).click();
        $(By.xpath("//*[contains(text(), 'Projekt Löschen')]")).click();
        driver.switchTo().alert().accept();
        closeWebDriver();
    }

    @Test
    public void openAny() throws InterruptedException {
        open("/");
        $(By.linkText("Element anlegen")).click();
        $(By.id("name")).sendKeys("DEMO-2");
        $(By.className("hudson_model_FreeStyleProject")).click();
        $(By.id("ok-button")).click();
        $(By.xpath("//*[contains(text(), 'Speichern')]")).click();
        $(By.xpath("//*[contains(text(), 'Zurück zur Übersicht')]")).click();

        Highlighter.highlight(($(By.id("job_DEMO-2")).shouldBe(visible)));

        Thread.sleep(2000);
    }
}
