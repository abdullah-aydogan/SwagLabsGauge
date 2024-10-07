package tr.abdullah.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;
import utils.AppiumUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest extends AppiumUtils {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws IOException {

        String propPath = System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties";

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(propPath);

        String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress")
                : prop.getProperty("ipAddress");

        prop.load(fis);

        String port = prop.getProperty("port");
        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName(prop.getProperty("androidDeviceName"));
        options.setAppPackage(prop.getProperty("appPackage"));
        options.setAppActivity(prop.getProperty("appActivity"));

        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        loginPage = new LoginPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {

        driver.quit();
        service.stop();
    }
}