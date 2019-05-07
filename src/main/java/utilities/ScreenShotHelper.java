package utilities;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Created by Prashant on 5/5/2019.
 */
public class ScreenShotHelper {

    public ScreenShotHelper() {
    }

    public static void takeScreenshot(WebDriver driver) {
        Allure.addAttachment("URL: ", driver.getCurrentUrl());

        try {
            Thread.sleep(500L);
            if (driver != null) {
                Allure.addAttachment("Screenshot", new ByteArrayInputStream(FileUtils.readFileToByteArray((File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE))));
            }
        } catch (Exception var2) {
            Log.error("Could not take screenshot. " + var2.getMessage());
        }

        if (driver == null) {
            Log.error("Webdriver is null, could not take screenshot at this point.");
        }

    }

    public static void takeScreenshot(WebDriver driver, String description) {
        Allure.addAttachment("URL: ", driver.getCurrentUrl());

        try {
            Thread.sleep(500L);
            if (driver != null) {
                Allure.addAttachment(description, new ByteArrayInputStream(FileUtils.readFileToByteArray((File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE))));
            }
        } catch (Exception var3) {
            Log.error("Could not take screenshot");
        }

        if (driver == null) {
            Log.error("Webdriver is null, could not take screenshot at this point.");
        }

    }
}
