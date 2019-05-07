package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.Log;
import utilities.ScreenShotHelper;
import utilities.WebDriverMethods;

import java.util.List;

/**
 * Created by Prashant on 5/4/2019.
 */
public class HomePage {


    WebDriver driver;

    private String topMenus = "//*[@id=\"block_top_menu\"]/ul/li";
    private String summerDresses = "//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[2]/ul/li[3]/a";
    private String printedChiffonDress = "//*[@id=\"center_column\"]/ul/li[3]/div/div[1]/div/a[1]/img";
    private String quickView = "//*[@id=\"center_column\"]/ul/li[3]/div/div[1]/div/a[2]";
    private String frame = "//*[@class=\"fancybox-iframe\"]";
    private String dressSize = "//*[@id=\"group_1\"]";
    private String addToCart = "//*[@id=\"add_to_cart\"]/button";
    private String productType = "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2";
    private String productSize = "//*[@id='layer_cart_product_attributes']";
    private String prodDescription = "//*[@id=\"layer_cart_product_title\"]";
    private String proceedCheckButton = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a";


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * @param name of the link and topmenu WebElement
     * @return matched link name
     */
    private WebElement topMenu(String name) {
        WebElement expectedLinkName = null;
        Log.info("finding all top menu Webelements");
        // finding all web elements of the top menu bar

        try {
            List<WebElement> topMenu = driver.findElements(By.xpath(topMenus));
            for (WebElement selection : topMenu) {
                if (selection.getText().equalsIgnoreCase(name)) {
                    Log.info("Found matching link name on top menu bar:- " + selection.getText());
                    expectedLinkName = selection;
                    break;
                }
            }

        } catch (Exception e) {
            Log.info("Exception occured at topMenu method in HomePage class");
            e.printStackTrace();
        }
        return expectedLinkName;
    }


    /**
     * @param name of the top navigation menu link
     */
    @Step("Step-01 Hover over link name: {0}...")
    public void hoverOverMenuLink(String name) {
        try {
            // Initializing actions class to do action events
            Actions actions = new Actions(driver);
            // passing name variable to the topMenu method
            actions.moveToElement(topMenu(name)).build().perform();
            // adding wait time for hove action
            Thread.sleep(3000);
            ScreenShotHelper.takeScreenshot(driver);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NullPointerException var2) {
            Log.error("driver is null " + var2.toString());
        }
    }

    @Step("Step-02 Click on Dress link")
    public void clickSummerDressLink() throws InterruptedException {

        try {

            WebElement el = driver.findElement(By.xpath(summerDresses));
            WebDriverMethods.highLighterMethod(driver, el);
            ScreenShotHelper.takeScreenshot(driver);
            Log.info("Clicking on Summer Dress");
            el.click();
            // adding wait time for hover action
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Log.error(e.getMessage());
        } catch (NoSuchElementException l) {
            Log.error("Not able to find element " + l.getMessage());

        }
    }

    /**
     * hover over printed chiffon dress
     */
    @Step("Step-03 Hover over chiffon dress")
    public void hoverOverChiffonDress() throws InterruptedException {
        try {
            // Initializing actions class to do action events
            Actions actions = new Actions(driver);
            Log.info("hover over printed chiffon dress");
            actions.moveToElement(driver.findElement(By.xpath(printedChiffonDress))).build().perform();
            // adding wait time for hover action
            Thread.sleep(3000);
            ScreenShotHelper.takeScreenshot(driver);
        } catch (NoSuchElementException e) {
            Log.error("Not able to find element " + e.getMessage());
        }
    }

    @Step("Step-04 Clicking Quick view link")
    public void clickQuickViewLink() throws InterruptedException {
        try {

            WebElement el = driver.findElement(By.xpath(quickView));
            WebDriverMethods.highLighterMethod(driver, el);
            ScreenShotHelper.takeScreenshot(driver);
            Log.info("Clicking on Quick View");
            el.click();
            // adding wait time
            Thread.sleep(5000);

        } catch (NoSuchElementException e) {
            Log.error("Not able to find element " + e.getMessage());
        }
    }

    @Step("Step-05 Switching focus to iframe")
    public void switchToIframe() {

        By by = By.xpath(frame);
        try {
            WebDriverMethods.moveToViewInIframe(driver, frame, by);
            Log.info("Focus switched to Iframe");
            ScreenShotHelper.takeScreenshot(driver);
        } catch (NoSuchElementException e) {
            Log.error("Not able to find element " + e.getMessage() + frame);
        }
    }

    @Step("Step-06 Selecting dress size: {0} ")
    public void selectDressSize(String size) {
        try {
            Select dropdown = new Select(driver.findElement(By.xpath(dressSize)));
            // Selecting dress size from variable
            dropdown.selectByVisibleText(size);
            Log.info("Selected dress size:- " + size);
            WebDriverMethods.highLighterMethod(driver, driver.findElement(By.xpath("//*[@id=\"group_1\"]")));
            ScreenShotHelper.takeScreenshot(driver);
        } catch (NoSuchElementException e) {
            Log.error("Not able to find element " + e.getMessage());
        }
    }

    @Step("Step-07 Clicking add to Cart button...")
    public void clickAddToCartButton() throws InterruptedException {

        try {
            WebElement addToCartButton = driver.findElement(By.xpath(addToCart));
            WebDriverMethods.highLighterMethod(driver, addToCartButton);
            ScreenShotHelper.takeScreenshot(driver);
            // clicking on add to cart button
            addToCartButton.click();
            Log.info("Clicked on Add to Cart Button on Iframe");
            Thread.sleep(3000);

            Log.info("Switching focus to active layer");
            driver.switchTo().activeElement();
        } catch (NoSuchElementException c) {
            Log.error("Not able to find element " + c.getMessage());
        }
    }

    public String getProductTypeSelected() {
        WebElement actualVerification = driver.findElement(By.xpath(productType));
        try {

            WebDriverMethods.waitForElementToBePresent(driver, productType);
            WebDriverMethods.highLighterMethod(driver, actualVerification);
            Log.info("Getting Product Type:- " + actualVerification.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return actualVerification.getText();
    }

    public String getProductSelectedSize() {
        WebElement size = driver.findElement(By.xpath(productSize));
        String[] actualSize = size.getText().split(",");
        WebDriverMethods.highLighterMethod(driver, size);
        Log.info("Getting Product dress size:- " + actualSize[1].trim());
        return actualSize[1].trim();
    }

    public String getProductTypeDescription() {
        WebElement productDescription = driver.findElement(By.xpath(prodDescription));
        String actualProductDescription = productDescription.getText();

        WebDriverMethods.highLighterMethod(driver, productDescription);
        Log.info("Getting Product Description:- " + actualProductDescription);
        return actualProductDescription;
    }

    @Step("Step-08 Clicking Proceed Checkout button...")
    public void clickToProceedCheckout() {
        try {
            WebElement proceedToCheckout = driver.findElement(By.xpath(proceedCheckButton));
            WebDriverMethods.highLighterMethod(driver, proceedToCheckout);
            ScreenShotHelper.takeScreenshot(driver);
            proceedToCheckout.click();
            Log.info("Clicked on proceed To Checkout Button");

        } catch (NoSuchElementException e) {
            Log.error("Not able to find ProceedCheckout button " + e.getMessage());
        }

    }

}

