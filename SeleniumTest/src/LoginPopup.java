import org.apache.xpath.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Created by Leny Oktavia on 6/9/2017.
 */
public class LoginPopup {
    WebDriver driver;
    Wait<WebDriver> wait;
    ActionBot action;

    By emailLocator = By.id("identifierId");
    By passwordLocator = By.name("password");
    By emailSubmitLocator = By.id("identifierNext");
    By passwordSubmitLocator = By.id("passwordNext");
    By mainPageIdentifier = By.id("drive_main_page");



    public LoginPopup(WebDriver driver) {
        this.driver = driver;
        action = new ActionBot(driver);
    }

    public MainPage login(String email, String password) {
        Boolean result=true;
        try {
            action.type(emailLocator,email);
            action.click(emailSubmitLocator);
            action.type(passwordLocator, password);
            action.click(passwordSubmitLocator);

        } catch(Exception e){
            fail(e.getMessage());
            result=false;
        }
        return new MainPage(driver);
        }
    }

