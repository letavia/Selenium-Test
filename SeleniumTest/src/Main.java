import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by Leny Oktavia on 6/8/2017.
 */
public class Main {
    static WebDriver driver = null;
    static Wait<WebDriver> wait;

    @BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testMain() throws InterruptedException {

        /** test login **/
        MainPageWithoutLogin mainPageWithoutLogin = new MainPageWithoutLogin(driver);
        LoginPopup loginPopup = mainPageWithoutLogin.openLoginPopup();
        MainPage mainPage = loginPopup.login("thelenyoktavia@gmail.com", "Sp!d3rn0v4");
        assertTrue("Login is not successful", mainPage.getUsername().contains("thelenyoktavia@gmail.com"));

        /** Test hover **/
        mainPage.hoverMenu();

        /** Test Search form **/
        String result=mainPage.search("lecture");
        assertTrue("Search is not successful",!result.equals("search results"));

        /** Test single static page **/
        String title=mainPage.openStaticPage();
        assertTrue("Open Static Pages is not successful",!title.equals("Privacy Policy – Privacy & Terms – Google"));

        /** Test multiple static pages **/
        mainPage.openStaticPages();

        /** Test logout **/
        mainPage.logout();
        assertTrue("Logout is not successful", driver.getCurrentUrl().contains("https://accounts.google.com"));
    }

}