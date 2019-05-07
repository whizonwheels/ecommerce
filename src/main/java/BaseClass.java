import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utilities.Log;
import utilities.ScreenShotHelper;

import java.util.concurrent.TimeUnit;

/**
 * Created by Prashant on 5/4/2019.
 */
public class BaseClass {

    protected WebDriver driver;
    protected String browser;
    protected String appUrl;

    @Parameters({"appUrl", "browser"})
    @BeforeClass()
    public void setUp(String appUrl, String browser) {
        this.appUrl = appUrl;
        this.browser = browser;
        driver = DriverInitializer.openBrowserNavigateToUrl(browser);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.navigate().to(appUrl);
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult testResult) {
        Log.info("Inside After Method");
        if (testResult.getStatus() == ITestResult.FAILURE) {
            Log.info("Test Failed");
            Allure.addAttachment("Test Failed", testResult.getThrowable().toString());
            ScreenShotHelper.takeScreenshot(driver);
        }

    }


    @AfterClass
    public void closeBrowser() {
        Log.info("Inside After Class, closing browser session...........");
        if (driver != null)
            driver.quit();
    }

}
