package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log;
import utilities.WebDriverMethods;

/**
 * Created by Prashant on 5/5/2019.
 */
public class Step_3_Address {

    WebDriver addressPage;

    private String proceedToCheckout = "//*[@id=\"center_column\"]/form/p/button"; //proceed to Checkout button

    public Step_3_Address(WebDriver driver) {
        this.addressPage=driver;
    }

    public void clickAddressProceedToCheckoutButton() {
        try {
            WebElement button = addressPage.findElement(By.xpath(proceedToCheckout));
            WebDriverMethods.highLighterMethod(addressPage, button);
            button.click();
            Log.info("Clicked on Proceed to checkout button on Address tab");
        } catch (NoSuchElementException e) {
            Log.error("Not able to find checkout button:- " + e.getMessage());
        }
    }

}
