import com.thoughtworks.selenium.webdriven.commands.FindFirstSelectedOptionProperty;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Leny Oktavia on 6/9/2017.
 */
public class MainPage extends PageObject {
    WebDriver driver;
    Wait<WebDriver> wait;
    ActionBot action;
    WebElement accountButton;
    WebElement accountPopup;

    @FindBy(id = "q")
    WebElement searchForm;

    @FindBy(xpath = "//*[@id='gb']/div[2]/div[3]/div/div[3]/div/a")
    WebElement account;

    @FindBy(id = "gb_71")
    WebElement signout;

    @FindBy(id = "drive_main_page")
    WebElement searchResultsText;

    @FindBy(xpath = "//*[@id='drive_main_page']/div[2]/div/div[1]/div/div/div[3]/div[2]/div/div[2]/div/div[2]/div/div[9]")
    WebElement settingsMenu;

    @FindBy(css = "body > div:nth-child(44)")
    WebElement settings;

    @FindBy(xpath = "//*[@id='gb']/div[2]/div[6]/div[1]/div/a")
    WebElement myAccount;

    @FindBy(linkText = "Privacy")
    WebElement privacyPolicy;

    By accountLocator = By.xpath("//*[@id='gb']/div[2]/div[3]/div/div[3]/div/a");
    By signoutLocator = By.id("gb_71");
    By searchFormLocator = By.name("q");
    By searchFormSubmitLocator = By.className("gb_De");
    By searchOptionsLocator = By.xpath("//*[@id='gb']/div[2]/div[2]/div/form/button[2]");
    By searchOptionsSubmitLocator = By.cssSelector("body > div.h-uj.a-Va-Zb-mb > form > div.a-Va-Zb-nb-j > div.h-sb-Ic.g-d.g-d-Y.g-d-W");
    By searchResultsTextLocator = By.id("drive_main_page");
    By SearchOptionsCheckBoxLocator = By.cssSelector("body > div.h-uj.a-Va-Zb-mb > form > div.a-Va-Zb-Q-j > div:nth-child(3) > div.a-Va-Zb-M > div > div.a-Va-Zb-Dh-ac > div:nth-child(2) > span.a-Va-Zb-Jk-Qa > span > div > div > svg > path:nth-child(2)");
    By settingsMenuLocator = By.xpath("//*[@id='drive_main_page']/div[2]/div/div[1]/div/div/div[3]/div[2]/div/div[2]/div/div[2]/div/div[9]");
    By settingsLocator = By.cssSelector(("body > div:nth-child(44)"));
    By myDriveMenuLocator = By.cssSelector("#drive_main_page > div.a-qc-La.sd-ph > div > div.a-s-tb-sc-Ja-Q.a-s-tb-sc-Ja-Q-Nm.a-s-tb-Pe-Q.a-D-Pe-Q > div > div > div.a-D-B-x > div.a-s-Ba-Ak.a-D-B-Ak.Hb-ja-hc > div > div:nth-child(2) > div > div.a-s-tb-sc-Ja-Q.a-s-tb-sc-Ja-Q-pa.a-Ba-Ed.a-s-Ba-ic > div > div");
    By myAccountLocator = By.xpath("//*[@id='gb']/div[2]/div[6]/div[1]/div/a");
    By langSettingsLocator = By.linkText("Change language settings");
    By settingsDone = By.xpath("//*[@class='h-sb-Ic g-d g-d-Y ca-k-An-d g-d-fe-vd' and text()='Done']");
    By languageSettings = By.xpath("//*[@id='i1']/div[2]/div/content/span/span");
    By selectLanguage = By.xpath("//*[@id='gd']/div[5]/div[2]/div[2]/div[3]");
    By privacyPolicyLocator = By.linkText("Privacy");

    String loginURL = "https://accounts.google.com";
    String privacyURL = "https://www.google.com/intl/en-GB/policies/privacy/";
    String myAccountURL = "https://myaccount.google.com/?utm_source=OGB&utm_medium=act&pli=1";


    public MainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        action = new ActionBot(driver);
        accountButton = action.waitForElement(accountLocator);
    }

    public String getUsername() {
        return accountButton.getAttribute("aria-label");
    }

    public void logout() {
        action.click(accountLocator);
        action.click(signoutLocator);
        action.pageURL(loginURL);
    }

    public String search(String query) throws InterruptedException {
        Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
                .withTimeout(20, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class);

        fWait.until(ExpectedConditions.visibilityOfElementLocated(searchFormLocator)).sendKeys(query+Keys.ENTER);

        String result = action.getText(searchResultsTextLocator);
        return result;

    }

    public void hoverMenu() throws InterruptedException {
        String winHandleBefore = driver.getWindowHandle();
        Actions builder = new Actions(driver);
        action.click(settingsMenuLocator);
        builder.sendKeys(Keys.DOWN).build().perform(); Thread.sleep(1000);
        builder.sendKeys(Keys.ENTER).build().perform();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        action.click(settingsDone);
        driver.switchTo().window(winHandleBefore);

    }

    public String openStaticPage() throws InterruptedException {
        String winHandleBefore = driver.getWindowHandle();
        action.click(accountLocator);
        action.click(privacyPolicyLocator);
        action.windowHandle();
        String title = privacyPolicy.getAttribute("title");
        driver.close();
        driver.switchTo().window(winHandleBefore); Thread.sleep(1000);
        return title;
    }

    public void openStaticPages() throws InterruptedException {
        String winHandleBefore = driver.getWindowHandle();
        List<By> locators = new ArrayList<>();
        locators.add(privacyPolicyLocator);
        locators.add(myAccountLocator);

        for (By temp : locators) {
            action.click(accountLocator);
            action.click(temp);
            action.windowHandle();
            Thread.sleep(3000);
            driver.close();
            driver.switchTo().window(winHandleBefore);
             }
        }


    }

