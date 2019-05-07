import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.Log;


/**
 * Created by Prashant on 5/4/2019.
 */
public class DriverInitializer {

    private static WebDriver driver;

    public DriverInitializer() {

    }

    public static WebDriver openBrowserNavigateToUrl(String browser) {
        driver = null;
        try {
            ChromeOptions options = new ChromeOptions();
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "chromedriver246.exe");
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                options.setAcceptInsecureCerts(true);
                options.addArguments(new String[]{"--disable-gpu"});
                options.addArguments(new String[]{"--start-maximized"});
                driver = new ChromeDriver(options);
                Log.info("Initializing browser :-" + options.getBrowserName());

            } else {
                if (browser.equalsIgnoreCase("chrome-headless")) {
                    System.setProperty("webdriver.chrome.driver", "chromedriver246.exe");
                    options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    options.setAcceptInsecureCerts(true);
                    options.addArguments(new String[]{"--disable-gpu"});
                    options.addArguments(new String[]{"--headless"});
                    options.addArguments("window-size=1200x600");
                    driver = new ChromeDriver(options);
                    Log.info("Initializing browser :-" + options.getBrowserName());
                }
            }
        } catch (Exception var9) {
            if (driver == null) {
                Log.error("Could not initialize " + browser +  " \n" + var9.toString());
            }

            return null;
        }

        if (driver != null) {
            Runtime var10000 = Runtime.getRuntime();
            WebDriver var10003 = driver;
            var10003.getClass();
            var10000.addShutdownHook(new Thread(var10003::quit));
            Log.info("Browser created.");
        } else {
            Log.error("Could not initialize " + browser);
        }

        Log.info("Driver successfully created: " + browser);
        return driver;
    }
}

