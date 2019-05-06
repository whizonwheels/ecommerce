/**
 * Created by Prashant on 4/29/2019.
 */

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.*;
import utilities.Log;
import utilities.WebDriverMethods;


public class FirstTest {

    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    WebDriver driver;

    //Declare a test URL variable
    public String testURL = "http://automationpractice.com/index.php";

    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest() {

        String path = "chromedriver246.exe";
        System.setProperty("webdriver.chrome.driver", path);
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--disable-gpu");
        //options.addArguments(new String[]{"--headless"});
        //options.addArguments("window-size=1200x600");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        Log.info("Initializing chrome browser :-" + options.getBrowserName());
        driver.navigate().to(testURL);
        Log.info("Navigating to page...." + testURL);
    }

    //This method will provide data to any test method that declares that its Data Provider
    //is named "endToEndTest"
    @DataProvider(name = "endToEndTest")
    public Object[][] createData1() {
        return new Object[][]{
                {"women", "M", "Product successfully added to your shopping cart", "Printed Chiffon Dress", "Test", "Automation", "123 hill st", "Happy123", "25", "3", "2002", "Acworth", "Georgia","7709701234","30101"},
                //{"women", "L", "Product successfully added to your shopping cart", "Printed Chiffon Dress"}},
        };
    }


    //-----------------------------------Tests-----------------------------------
    @Test(dataProvider = "endToEndTest")
    @Description("Test Description: Select a product from online store, create an account and checkout")
    public void endToEndTest(String gender, String size, String prodSelected, String prodType, String fName, String lName, String address, String password, String day,
                             String month, String year, String city, String state, String phone, String zip) throws InterruptedException {
        //-----------------------------------Home Page Initialize-----------------------------------
        HomePage home = new HomePage(driver);
        //Hover over Women
        home.hoverOverMenuLink(gender);
        //Clicking on Summer Dress
        home.clickSummerDressLink();
        //hover over printed chiffon dress
        home.hoverOverChiffonDress();
        //Clicking on Quick View
        home.clickQuickViewLink();
        // Switching focus to iframe
        home.switchToIframe();
        // select dress size
        home.selectDressSize(size);
        //clicking add to cart
        home.clickAddToCartButton();
        // verifying product is added to the cart
        Assert.assertEquals(home.getProductTypeSelected(), prodSelected, "Cart text assertion failed!");
        // verifying product description is correct
        Assert.assertEquals(home.getProductTypeDescription(), prodType, "Product Description assertion failed!");
        // verifying product size is correct
        Assert.assertEquals(home.getProductSelectedSize(), size, "Size assertion failed!");
        // clicking on proceed checkout
        home.clickToProceedCheckout();
        //-----------------------------------Shopping Cart Initialize-----------------------------------
        //Initialize page
        ShoppingCartSummary cart = new ShoppingCartSummary(driver);
        // verifying the cart title on summary tab
        Assert.assertEquals(cart.getCartTitle(),"SHOPPING-CART SUMMARY", "Cart Title doesn't match");
        // verifying summary tab is selected
        Assert.assertTrue(cart.isStepSelected("01. Summary"));
        Log.info("01. Summary step the shopping cart summary is selected.......");
        WebDriverMethods.highLighterMethod(driver, cart.getSummaryNav());
        // verifying product description is correct
        Assert.assertEquals(cart.getProductType(), prodType, "shopping cart description doesn't match");
        // verifying product size is correct
        Assert.assertEquals(cart.getProductSize(), size, "shopping cart product size doesn't match");
        // clicking on proceed checkout
        cart.clickProceedToCheckout();
        //-----------------------------------Step 2 Sign In------------------------------------------
        //Initialize page
        CreateAccount account = new CreateAccount(driver);
        Log.info("02. Sign In step is selected.......");
        Assert.assertTrue(cart.isStepSelected("02. Sign in"));
        WebDriverMethods.highLighterMethod(driver, cart.getSignInNav());

        // Entering email address
        account.setCreateAccountEmail("apple_" + WebDriverMethods.getRandomNumber(driver) + "@gmail.com");
        // Clicking on create account button
        account.clickCreateAccountButton();

        //-----------------------------------Creating Account form-----------------------------------
        CreateAccount form = new CreateAccount(driver);
        // selected title Mr.
        form.selectMaleGender();
        // Entering First name
        form.enterFirstName(fName);
        // Entering Last name
        form.enterLastName(lName);

        form.isEmailSameAsBefore("apple@a.com");
        // Entering password
        form.enterPassword(password);
        // Entering Day of Birth
        form.selectDay(day);
        // Entering Month of Birth
        form.selectMonth(month);
        // Entering Year of Birth
        form.selectYear(year);
        // Entering address
        form.enterAddress(address);
        // Entering city
        form.enterCity(city);
        // Entering State
        form.enterState(state);
        // Entering Zip code
        form.enterZipCode(zip);
        // Entering Mobile phone
        form.enterMobilePhone(phone);
        // Clicking on Register button
        form.clickRegisterButton();
        //-----------------------------------Step 3 Address----------------------------------------------------
        //Initialize page
        Step_3_Address third = new Step_3_Address(driver);
        // verifying correct tab is selected
        Assert.assertTrue(cart.isStepSelected("03. Address"));
        Log.info("03. Address step is selected.......");
        WebDriverMethods.highLighterMethod(driver, cart.getAddressNav());
        // Clicking on Proceed To Checkout button
        third.clickAddressProceedToCheckoutButton();
        //-----------------------------------Step 4 Shipping----------------------------------------------------
        //Initialize page
        Step_4_Shipping fourth = new Step_4_Shipping(driver);
        // verifying correct tab is selected
        Assert.assertTrue(cart.isStepSelected("04. Shipping"));
        Log.info("04. Shipping step is selected.......");
        WebDriverMethods.highLighterMethod(driver, cart.getShippingNav());
        // Accepting Terms
        fourth.acceptTerms();
        // Clicking on Proceed To Checkout button
        fourth.clickShippingProceedToCheckoutButton();
        //-----------------------------------Step 5 Payment----------------------------------------------------
        //Initialize page
        Step_5_Payment five = new Step_5_Payment(driver);
        // verifying correct tab is selected
        Assert.assertTrue(cart.isStepSelected("05. Payment"));
        Log.info("05. Payment step is selected.......");
        WebDriverMethods.highLighterMethod(driver, cart.getPaymentNav());
        // verifying product description is correct
        Assert.assertEquals(five.verifyProdDescPaymentTab(), prodType, "Product Description assertion failed!");
        // verifying product size is correct
        Assert.assertEquals(five.verifyProdSizePaymentTab(), size, "Size assertion failed!");

    }

    //-----------------------------------Test TearDown---------------------------------------------------------
    @AfterMethod
    public void teardownTest() {
        //Close browser and end the session
        Log.endLog("Finishing test, closing the browser session");
        if (driver != null)
            driver.quit();
    }

}
