package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

/**
 * Created by Prashant on 5/5/2019.
 */
public class WebDriverMethods {

    private static final int maxPageLoadTime = 30;
    private static final int maxFrameLoadTime = 30;
    private static final int maxWaitElementPresenceTime = 5000;
    private static final String MIDDLE_SCREEN_COORDENATES = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);var elementTop = arguments[0].getBoundingClientRect().top;window.scrollBy(0, elementTop-(viewPortHeight/2));";

    public WebDriverMethods() {

    }

    public static void moveToViewInIframe(WebDriver driver, String frameName, By by) {
        waitForElementToBePresent(driver, frameName);
        WebElement element = driver.findElement(by);

        //while(!isVisibleInViewport(driver, element)) {
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("scroll(0," + element.getLocation() + ")", new Object[0]);
            Point loc = element.getLocation();
            loc.x = element.getLocation().getX() - 200;
            loc.y = element.getLocation().getY() - 200;
            driver.switchTo().parentFrame();
            jse.executeScript("scroll(0," + loc + ")", new Object[0]);
            driver.switchTo().frame(element.getAttribute("name"));
        //}
    }

    public static void waitForElementToBePresent(WebDriver driver, String xpathLocator) {
        waitForElementToBePresent(driver, (String)xpathLocator, 5000);
    }

    private static Boolean isVisibleInViewport(WebDriver driver, WebElement element) {
        return (Boolean)((JavascriptExecutor)driver).executeScript("var elem = arguments[0],                   box = elem.getBoundingClientRect(),      cx = box.left + box.width / 2,           cy = box.top + box.height / 2,           e = document.elementFromPoint(cx, cy); for (; e; e = e.parentElement) {           if (e === elem)                            return true;                         }                                        return false;                            ", new Object[]{element});
    }

    public static void waitForElementToBePresent(WebDriver driver, String xpathLocator, int maxTimeInMilliseconds) {
        By byLocator = By.xpath(xpathLocator);
        waitForElementToBePresent(driver, byLocator, maxTimeInMilliseconds);
    }

    public static void waitForElementToBePresent(WebDriver driver, By locator, int maxTimeInMilliseconds) {
        try {
            Thread.sleep(500L);
        } catch (Exception var4) {
        }
    }

    public static void highLighterMethod(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        Log.info("Highlighting element....." + element);
    }

    public static void waitForElementToBeVisibleId(WebDriver driver, String idLocator ,int timeInMilliseconds) {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, timeInMilliseconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(idLocator))).click();
    }

    public static int getRandomNumber(WebDriver driver) {
        Random rand = new Random();
        int value = rand.nextInt(50);
        return value;
    }
}
