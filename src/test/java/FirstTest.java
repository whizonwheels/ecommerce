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
    public WebDriver driver;

    //Declare a test URL variable
    public String testURL = "http://automationpractice.com/index.php";

    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest() {

        String path = "D:\\chromedriver\\chromedriver246.exe";
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
                //{"women", "L"},
        };
    }


    //-----------------------------------Tests-----------------------------------
    @Test(dataProvider = "endToEndTest")
    public void endToEndTest(String gender, String size, String prodSelected, String prodType) throws InterruptedException {
        //-----------------------------------Home Page-----------------------------------
        HomePage home = new HomePage(driver);

        home.hoverOverMenuLink(gender);
        home.clickSummerDressLink();
        home.hoverOverChiffonDress();
        home.clickQuickViewLink();
        home.switchToIframe();
        home.selectDressSize(size);
        home.clickAddToCartButton();
        Assert.assertEquals(home.getProductTypeSelected(), prodSelected, "Cart text assertion failed!");
        Assert.assertEquals(home.getProductTypeDescription(), prodType, "Product Description assertion failed!");
        Assert.assertEquals(home.getProductSelectedSize(), size, "Size assertion failed!");
        home.clickToProceedCheckout();
        //-----------------------------------Shopping Cart-----------------------------------
        ShoppingCartSummary cart = new ShoppingCartSummary(driver);
        Assert.assertEquals(cart.getCartTitle(),"SHOPPING-CART SUMMARY", "Cart Title doesn't match");

        Assert.assertTrue(cart.isStepSelected("01. Summary"));
        Log.info("01. Summary step the shopping cart summary is selected.......");
        WebDriverMethods.highLighterMethod(driver, cart.getSummaryNav());

        Assert.assertEquals(cart.getProductType(), prodType, "shopping cart description doesn't match");
        Assert.assertEquals(cart.getProductSize(), size, "shopping cart product size doesn't match");
        cart.clickProceedToCheckout();
        //-----------------------------------Step 2 Sign In------------------------------------------

        CreateAccount account = new CreateAccount(driver);
        Log.info("02. Sign In step is selected.......");
        Assert.assertTrue(cart.isStepSelected("02. Sign in"));
        WebDriverMethods.highLighterMethod(driver, cart.getSignInNav());

        account.setCreateAccountEmail("apple_" + WebDriverMethods.getRandomNumber(driver) + "@gmail.com");
        account.clickCreateAccountButton();


        // ========================enter form details
/*
        List<WebElement> gender = driver.findElements(By.className("radio-inline"));
        for (WebElement abc: gender)
            abc.click();
*/
        //-----------------------------------Creating Account form-----------------------------------
        CreateAccount form = new CreateAccount(driver);
        form.selectMaleGender();
        form.enterFirstName("Ram");
        form.enterLastName("Khishan");
        //form.enterEmail("abc@gmail.com");
        form.isEmailSameAsBefore("apple@a.com");
        form.enterPassword("Happy123");
        form.selectDay("25");
        form.selectMonth("3");
        form.selectYear("2002");

        form.enterAddress("27 abc crescent");
        form.enterCity("Acworth");
        form.enterState("Georgia");
        form.enterZipCode("30101");
        form.enterMobilePhone("7709701234");
        form.clickRegisterButton();
        //-----------------------------------Step 3 Address----------------------------------------------------
        Step_3_Address third = new Step_3_Address(driver);
        Assert.assertTrue(cart.isStepSelected("03. Address"));
        Log.info("03. Address step is selected.......");
        WebDriverMethods.highLighterMethod(driver, cart.getAddressNav());
        third.clickAddressProceedToCheckoutButton();
        //-----------------------------------Step 4 Shipping----------------------------------------------------
        Step_4_Shipping fourth = new Step_4_Shipping(driver);
        Assert.assertTrue(cart.isStepSelected("04. Shipping"));
        Log.info("04. Shipping step is selected.......");
        WebDriverMethods.highLighterMethod(driver, cart.getShippingNav());
        fourth.acceptTerms();
        fourth.clickShippingProceedToCheckoutButton();
        //-----------------------------------Step 5 Payment----------------------------------------------------
        Step_5_Payment five = new Step_5_Payment(driver);
        Assert.assertTrue(cart.isStepSelected("05. Payment"));
        Log.info("05. Payment step is selected.......");
        WebDriverMethods.highLighterMethod(driver, cart.getPaymentNav());
        Assert.assertEquals(five.verifyProdDescPaymentTab(), prodType, "Product Description assertion failed!");
        Assert.assertEquals(five.verifyProdSizePaymentTab(), size, "Size assertion failed!");



        System.out.println("Hurray");
        Thread.sleep(8000);
    }

    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        //Close browser and end the session
        if (driver != null)
            driver.quit();
    }

}
