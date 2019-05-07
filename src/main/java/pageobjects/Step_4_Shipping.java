package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log;
import utilities.ScreenShotHelper;
import utilities.WebDriverMethods;

/**
 * Created by Prashant on 5/5/2019.
 */
public class Step_4_Shipping {

    WebDriver shippingPage;

    private String proceedToCheckout = "//*[@id=\"form\"]/p/button"; //proceed to Checkout button
    private String termsOfService = "cgv";

    public Step_4_Shipping(WebDriver driver) {
        this.shippingPage=driver;
    }

    @Step("Step-12 Clicking Proceed To Checkout button on step 4...")
    public void clickShippingProceedToCheckoutButton() {
        try {
            WebElement button = shippingPage.findElement(By.xpath(proceedToCheckout));
            WebDriverMethods.highLighterMethod(shippingPage, button);
            ScreenShotHelper.takeScreenshot(shippingPage);
            button.click();
            Log.info("Clicked on Proceed to checkout button on Shipping tab");

        } catch (NoSuchElementException e) {
            Log.error("Not able to find webelement " + e.getMessage());
        }
    }

    public void acceptTerms() {
        try {
            WebElement terms = shippingPage.findElement(By.id(termsOfService));
            // Clicking on Terms check box
            terms.click();
            WebDriverMethods.highLighterMethod(shippingPage,terms);
            Log.info("Checked terms of service checkbox on shipping tab");
        } catch (NoSuchElementException e) {
            Log.error("Not able to find terms checkbox" + e.getMessage());
        }
    }

}
