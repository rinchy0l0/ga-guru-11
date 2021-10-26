package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import helpers.Attach;
import static java.lang.String.format;

public class TestBase {
    static CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
    static String loginForSelenoid = credentials.login();
    static String passwordForSelenoid = credentials.password();

    @BeforeAll
    static void setup() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
        //   Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";

        Configuration.remote =
                format("https://%s:%s@%s", loginForSelenoid, passwordForSelenoid, System.getProperty("remoteUrl"));
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
