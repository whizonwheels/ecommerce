package pageobjects;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.Log;
import utilities.WebDriverMethods;

/**
 * Created by Prashant on 5/1/2019.
 */
public class CreateAccount {

    WebDriver page;

    private String createAccountButton = "//*[@id=\"SubmitCreate\"]";
    private String accountEmail = "//*[@id=\"email_create\"]";

    private String labelPersonalInfo = "//*[@id=\"account-creation_form\"]/div[1]/h3";  //YOUR PERSONAL INFORMATION
    private String genderMale = "id_gender1";
    private String genderFemale = "id_gender2";
    private String cusfirstName = "customer_firstname"; // mandatory field
    private String cuslastName = "customer_lastname"; // mandatory field
    private String emailAddress = "email"; // mandatory field
    private String password = "passwd"; // mandatory field
    private String day = "days";
    private String month = "months";
    private String year = "years";
    private String checkboxNewsletter = "newsletter";
    private String checkboxSpecialOffer = "optin";

    private String labelYourAdd = "//*[@id=\"account-creation_form\"]/div[2]/h3";  // YOUR ADDRESS
    private String firstName = "firstname"; // mandatory field
    private String lastName = "lastname";   // mandatory field //*[@id="account-creation_form"]/div[1]/div[6]/label // 1-8
    private String address1 = "address1"; // mandatory field
    private String address2 = "address2";
    private String formCity = "city"; // mandatory field
    private String state = "id_state"; // mandatory field
    private String postalCode = "postcode"; // mandatory field
    private String country = "id_country"; // mandatory field
    private String additonalInfo = "other";
    private String homePhone = "phone";
    private String mobilePhone = "phone_mobile"; // mandatory field
    private String aliasForFuture = "alias"; // mandatory field but already pre-populated with value=My address
    private String registerButton = "//*[@id=\"submitAccount\"]";


    public CreateAccount(WebDriver driver) {
        this.page = driver;
    }

    public void setCreateAccountEmail(String emailAddress) {
        try {
            // enter mail address
            WebElement enterEmail = page.findElement(By.xpath(accountEmail));
            WebDriverMethods.highLighterMethod(page, enterEmail);
            //clearing the email input field
            enterEmail.clear();
            //Entering email address
            enterEmail.sendKeys(emailAddress);
            Log.info("Entered email address to create an account:- " + emailAddress);
        } catch (NoSuchElementException e) {
            Log.error("Not able to find email input field:- " + e.getMessage());
        }
    }

    public void clickCreateAccountButton() {
        // click proceed to checkout button
        try {
            WebElement proceedCheckoutButton = page.findElement(By.xpath(createAccountButton));
            WebDriverMethods.highLighterMethod(page, proceedCheckoutButton);
            proceedCheckoutButton.click();
            Log.info("Clicked on Create an account button");
        } catch (NoSuchElementException e) {
            Log.error("Not able to find element " + e.getMessage());
        }
    }

    public String getPersonalInfoLabelText() {
        return page.findElement(By.xpath(labelPersonalInfo)).getText().trim();
    }

    public void selectMaleGender() {
        Log.info("selecting male gender");
        WebDriverMethods.waitForElementToBeVisibleId(page, genderMale, 5000);
        page.findElement(By.id(genderMale)).click();
    }

    public void selectFemaleGender() {
        page.findElement(By.id(genderFemale)).click();
    }

    public void enterFirstName(String custFirstName) {
        Log.info("Entering First Name :- " + custFirstName);
        page.findElement(By.id(cusfirstName)).sendKeys(custFirstName);
    }

    public void enterLastName(String cusLastName) {
        Log.info("Entering Last Name :- " + cusLastName);
        page.findElement(By.id(cuslastName)).sendKeys(cusLastName);
    }

    private void clearEmailAddField() {
        page.findElement(By.id(emailAddress)).clear();
    }

    public void enterEmail(String emailAdd) {
        // clearing the email field as it is pre-populated with email from the previous step
        clearEmailAddField();
        // entering email address in the field
        page.findElement(By.id(emailAddress)).sendKeys(emailAdd);
    }

    private String getEnteredEmail() {
        return page.findElement(By.id(emailAddress)).getText();
    }

    public boolean isEmailSameAsBefore(String emailAdd) {
        boolean val = false;
        if (getEnteredEmail().equalsIgnoreCase(emailAdd)) {
            val = true;
            Log.info("Email address in the email field matched with the email entered:- " + emailAdd);
        }else {

            Log.error("Email address in the email field not matched with the email entered:- " + emailAdd);
        }

        return val;
    }

    public void enterPassword(String emailPassword) {
        Log.info("Entering Password in the field :- " + emailPassword);
        page.findElement(By.id(password)).sendKeys(emailPassword);
    }

    public void selectDay(String birthDay) {
        Select val = new Select(page.findElement(By.id(day)));
        Log.info("Entering Day of Birth:- " + birthDay);
        val.selectByValue(birthDay);
    }

    public void selectMonth(String birthMonth) {
        Select val = new Select(page.findElement(By.id(month)));
        Log.info("Entering Month of Birth:- " + birthMonth);
        val.selectByValue(birthMonth);
    }

    public void selectYear(String birthYear) {
        try {
            Select val = new Select(page.findElement(By.id(year)));
            Log.info("Entering Year of Birth:- " + birthYear);
            val.selectByValue(birthYear);
        } catch (NoSuchElementException e) {
            Log.error("Not able to find element " + e.getMessage());
        }

    }

    public void enterAddress(String address) {

        try {
            WebElement el = page.findElement((By.id(address1)));
            Log.info("Entering Address :- " + address);
            WebDriverMethods.highLighterMethod(page, el);
            el.sendKeys(address);
        } catch (NoSuchElementException e) {
            Log.error("Not able to find element " + e.getMessage());
        }

    }

    public void enterCity(String cityName) {

        try {
            WebElement el = page.findElement((By.id(formCity)));
            Log.info("Entering City name:- " + cityName);
            WebDriverMethods.highLighterMethod(page, el);
            el.sendKeys(cityName);
        } catch (NoSuchElementException e) {
            Log.error("Not able to find element " + e.getMessage());
        }

    }

    public void enterState(String stateName) {
        try {
            Select val = new Select(page.findElement(By.id(state)));
            Log.info("Selecting State:- " + stateName);
            val.selectByVisibleText(stateName);
        } catch (NoSuchElementException e) {
            Log.error("Not able to find element " + e.getMessage());
        }

    }

    public void enterZipCode(String zipPostCode) {

        try {
            WebElement el = page.findElement((By.id(postalCode)));
            Log.info("Entering Zip Code:- " + zipPostCode);
            WebDriverMethods.highLighterMethod(page, el);
            el.sendKeys(zipPostCode);
        } catch (NoSuchElementException e) {
            Log.error("Not able to find element " + e.getMessage());
        }

    }

    public void enterMobilePhone(String mobileNumber) {

        try {
            WebElement el = page.findElement((By.id(mobilePhone)));
            Log.info("Entering Mobile Number:- " + mobileNumber);
            WebDriverMethods.highLighterMethod(page, el);
            el.sendKeys(mobileNumber);
        } catch (NoSuchElementException e) {
            Log.error("Not able to find element " + e.getMessage());
        }

    }

    public void clickRegisterButton() {

        try {
            WebElement el = page.findElement(By.xpath(registerButton));
            WebDriverMethods.highLighterMethod(page, el);
            el.click();
            Log.info("Clicked on Create an account button");
        } catch (NoSuchElementException e) {
            Log.error("Not able to find element " + e.getMessage());
        }
    }


}
