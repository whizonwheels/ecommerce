/**
 * Created by Prashant on 4/29/2019.
 */

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
                {"women", "M", "Product successfully added to your shopping cart", "Printed Chiffon Dress"},
                //{"women", "L", "Product successfully added to your shopping cart", "Printed Chiffon Dress"}},
        };
    }


    //-----------------------------------Tests-----------------------------------
    @Test(dataProvider = "endToEndTest")
    public void endToEndTest(String gender, String size, String prodSelected, String prodType) throws InterruptedException {
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
        form.enterFirstName("Ram");
        // Entering Last name
        form.enterLastName("Khishan");

        form.isEmailSameAsBefore("apple@a.com");
        // Entering password
        form.enterPassword("Happy123");
        // Entering Day of Birth
        form.selectDay("25");
        // Entering Month of Birth
        form.selectMonth("3");
        // Entering Year of Birth
        form.selectYear("2002");
        // Entering address
        form.enterAddress("27 abc crescent");
        // Entering city
        form.enterCity("Acworth");
        // Entering State
        form.enterState("Georgia");
        // Entering Zip code
        form.enterZipCode("30101");
        // Entering Mobile phone
        form.enterMobilePhone("7709701234");
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
