package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.Log;
import utilities.WebDriverMethods;

import java.util.List;

/**
 * Created by Prashant on 5/4/2019.
 */
public class HomePage {

    WebDriver driver;

    private static final String topMenus = "//*[@id=\"block_top_menu\"]/ul/li";
    private static final String summerDresses = "//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[2]/ul/li[3]/a";
    private static final String printedChiffonDress = "//*[@id=\"center_column\"]/ul/li[3]/div/div[1]/div/a[1]/img";
    private static final String quickView = "//*[@id=\"center_column\"]/ul/li[3]/div/div[1]/div/a[2]";
    private static final String frame = "//*[@class=\"fancybox-iframe\"]";
    private static final String dressSize = "//*[@id=\"group_1\"]";
    private static final String addToCart = "//*[@id=\"add_to_cart\"]/button";
    private static final String productType = "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2";
    private static final String productSize = "//*[@id='layer_cart_product_attributes']";
    private static final String prodDescription = "//*[@id=\"layer_cart_product_title\"]";
    private static final String proceedCheckButton = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a";


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     *
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
     *
     * @param name of the top navigation menu link
     */
    public void hoverOverMenuLink(String name) {
        try {
            // Initializing actions class to do action events
            Actions actions = new Actions(driver);
            // passing name variable to the topMenu method
            actions.moveToElement(topMenu(name)).build().perform();
            // adding wait time for hove action
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void clickSummerDressLink() throws InterruptedException {
        Log.info("Clicking on Summer Dress");
        driver.findElement(By.xpath(summerDresses)).click();
        // adding wait time for hover action
        Thread.sleep(3000);
    }

    /**
     * hover over printed chiffon dress
     */
    public void hoverOverChiffonDress() {
        try {
            // Initializing actions class to do action events
            Actions actions = new Actions(driver);
            Log.info("hover over printed chiffon dress");
            actions.moveToElement(driver.findElement(By.xpath(printedChiffonDress))).build().perform();
            // adding wait time for hover action
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void clickQuickViewLink() throws InterruptedException {
        Log.info("Clicking on Quick View");
        driver.findElement(By.xpath(quickView)).click();
        // adding wait time
        Thread.sleep(5000);
    }

    public void switchToIframe() {

        By by = By.xpath(frame);
        WebDriverMethods.moveToViewInIframe(driver,frame, by);
        Log.info("Focus switched to Iframe");
    }

    public void selectDressSize(String size) {
        Select dropdown = new Select(driver.findElement(By.xpath(dressSize)));
        // Selecting dress size from variable
        dropdown.selectByVisibleText(size);
        Log.info("Selected dress size:- " + size);
        WebDriverMethods.highLighterMethod(driver, driver.findElement(By.xpath("//*[@id=\"group_1\"]")));
    }

    public void clickAddToCartButton() throws InterruptedException {

        WebElement addToCartButton = driver.findElement(By.xpath(addToCart));
        WebDriverMethods.highLighterMethod(driver, addToCartButton);
        // clicking on add to cart button
        addToCartButton.click();
        Log.info("Clicked on Add to Cart Button on Iframe");
        Thread.sleep(3000);
        Log.info("Switching focus to active layer");
        driver.switchTo().activeElement();
    }

    public String getProductTypeSelected() {
       // Thread.sleep(3000);
        WebElement actualVerification = driver.findElement(By.xpath(productType));
        WebDriverMethods.waitForElementToBePresent(driver, productType);
        WebDriverMethods.highLighterMethod(driver, actualVerification);
        Log.info("Getting Product Type:- " + actualVerification.getText());
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

    public void clickToProceedCheckout() {
        WebElement proceedToCheckout = driver.findElement(By.xpath(proceedCheckButton));
        WebDriverMethods.highLighterMethod(driver, proceedToCheckout);
        proceedToCheckout.click();
        Log.info("Clicked on proceed To Checkout Button");
        // Thread.sleep(2000);
    }

}

