package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log;
import utilities.ScreenShotHelper;
import utilities.WebDriverMethods;

import java.util.List;

/**
 * Created by Prashant on 5/5/2019.
 */
public class ShoppingCartSummary {

    WebDriver driver;

    private String cartTitle = "//*[@id=\"cart_title\"]";
    private String shoppingCartNav = "#order_step li";
    private String shoppingCartProductDesc = "td.cart_description  p  a";
    private String shoppingCartProductSize = "td.cart_description  small a";
    private String shoppingCartProceedCheckout = "//*[@id=\"center_column\"]/p[2]/a[1]";


    public ShoppingCartSummary(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @return shopping cart title
     */
    public String getCartTitle() {

        String actualText = null;
        try {
            WebElement title = driver.findElement(By.xpath(cartTitle));
            WebDriverMethods.highLighterMethod(driver, title);
            // split the text as more values are obtained
            String[] actual = title.getText().split("\n");
            actualText = actual[0].trim();
            Log.info("Getting shopping cart title:- " + actualText);
        } catch (NoSuchElementException e) {
            Log.error("Not able to find web element" + e.toString());
            throw(e);
        } catch (IndexOutOfBoundsException i) {
            Log.error("Not able to find cart title text...step 1" + i.toString());
            throw (i);
        }
        return actualText;
    }

    /**
     * @param step
     * @return boolean if the entered step is the current step
     */
    public boolean isStepSelected(String step) {
        boolean selected = false;
        List<WebElement> steps = driver.findElements(By.cssSelector(shoppingCartNav));
        for (WebElement el : steps) {
            if (el.getAttribute("class").toString().contains("step_current")) {
                System.out.println("current step is:-" + el.getAttribute("class").toString());
                String[] val = el.getAttribute("class").toString().split("step_current");
                String stepName = el.getText();
                if (stepName.equalsIgnoreCase(step)) {
                    System.out.println("current step class:-" + val[1].trim() + "\n" + "Step name:-" + stepName);
                    selected = true;
                    ScreenShotHelper.takeScreenshot(driver,val[1]);
                    break;
                }
            }
        }
        return selected;
    }

    public WebElement getSummaryNav() {
        return driver.findElements(By.cssSelector(shoppingCartNav)).get(0);
    }

    public WebElement getSignInNav() {
        return driver.findElements(By.cssSelector(shoppingCartNav)).get(1);
    }

    public WebElement getAddressNav() {
        return driver.findElements(By.cssSelector(shoppingCartNav)).get(2);
    }

    public WebElement getShippingNav() {
        return driver.findElements(By.cssSelector(shoppingCartNav)).get(3);
    }

    public WebElement getPaymentNav() {
        return driver.findElements(By.cssSelector(shoppingCartNav)).get(4);
    }

    public String getProductType() {
        //Verifying product type is correct in the shopping cart
        WebElement productType = driver.findElement(By.cssSelector(shoppingCartProductDesc));
        try {
            WebDriverMethods.highLighterMethod(driver, productType);
            Log.info("Verifying product type is correct in the shopping cart:- " + productType.getText().trim());

        } catch (NoSuchElementException e) {
            Log.error("Not able to find product description element " + e.getMessage());
        }
        return productType.getText().trim();
    }

    public String getProductSize() {
        //Verifying product type is correct in the shopping cart
        WebElement productSize = driver.findElement(By.cssSelector(shoppingCartProductSize));
        String [] actualSize = new String[0];
        try {
            WebDriverMethods.highLighterMethod(driver, productSize);
            actualSize = productSize.getText().split(":");
            Log.info("Verifying product size is correct in the shopping cart:- " + actualSize[2]);
            ScreenShotHelper.takeScreenshot(driver);
        } catch (NoSuchElementException e) {
            Log.error("Not able to find product size element " + e.getMessage());
        }
        return actualSize[2].trim();
    }

    @Step("Step-09 Clicking Proceed Checkout button on step 1...")
    public void clickProceedToCheckout() {
        // proceed to checkout
        try {
            WebElement proceedToCheckout1 = driver.findElement((By.xpath(shoppingCartProceedCheckout)));
            WebDriverMethods.highLighterMethod(driver, proceedToCheckout1);
            ScreenShotHelper.takeScreenshot(driver);
            proceedToCheckout1.click();
            Log.info("clicked on shopping cart proceed checkout button");

        } catch (NoSuchElementException e) {
            Log.error("Not able to find Webelement " + e.getMessage());
        }
    }


}
