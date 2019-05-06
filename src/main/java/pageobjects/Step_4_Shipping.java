package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log;

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

    public void clickShippingProceedToCheckoutButton() {
        try {
            WebElement button = shippingPage.findElement(By.xpath(proceedToCheckout));
            button.click();
            Log.info("Clicked on Proceed to checkout button on Shipping tab");
        } catch (Error e) {
            Log.error(e.getMessage());
        }
    }

    public void acceptTerms() {
        try {
            WebElement terms = shippingPage.findElement(By.id(termsOfService));
            terms.click();
            Log.info("Checked terms of service checkbox on shipping tab");
        } catch (Exception e) {
            Log.error(e.getMessage());
        }
    }

}
