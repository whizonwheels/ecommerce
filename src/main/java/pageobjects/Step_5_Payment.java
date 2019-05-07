package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log;
import utilities.WebDriverMethods;

/**
 * Created by Prashant on 5/5/2019.
 */
public class Step_5_Payment {

    WebDriver driver;
    ShoppingCartSummary cart;

    public Step_5_Payment(WebDriver driver) {
        this.driver = driver;
        this.cart = new ShoppingCartSummary(driver);
    }

    public String verifyProdDescPaymentTab() throws InterruptedException {
        Thread.sleep(3000);
        return cart.getProductType();
    }

    @Step("Step-13 Verifying Product type and Size on step 5...")
    public String verifyProdSizePaymentTab() {
        return cart.getProductSize();
    }




}
