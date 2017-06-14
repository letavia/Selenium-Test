import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Leny Oktavia on 6/9/2017.
 */

public class MainPageWithoutLogin {
    WebDriver driver;
    Wait<WebDriver> wait;
    ActionBot action;

    String startURL = "https://www.google.com/drive/";
    By loginLocator = By.linkText("Go to Google Drive");

    public MainPageWithoutLogin(WebDriver driver) {
        this.driver = driver;
        action = new ActionBot(driver);
    }

    public LoginPopup openLoginPopup(){
        driver.get(startURL);
        driver.manage().window().maximize();
        action.click(loginLocator);
        return new LoginPopup(driver);
    }
}
