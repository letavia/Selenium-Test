import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by Leny Oktavia on 6/10/2017.
 */

    public class ActionBot {
        WebDriver driver;

        public ActionBot(WebDriver driver)
        {
            this.driver = driver;
        }

        public void click(By locator)
        {
            (new WebDriverWait(driver, 10)).until
                    (ExpectedConditions.visibilityOfElementLocated(locator));
            driver.findElement(locator).click();
        }

        public void browseclick(By locator, int index) throws IOException {
            (new WebDriverWait(driver, 30)).until
                    (ExpectedConditions.visibilityOfElementLocated(locator));
            List<WebElement> browse = driver.findElements(locator);
            browse.get(index).click();

        }

        public void type(By locator, String value)
        {
            (new WebDriverWait(driver, 10)).until
                    (ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(value);
        }

        public void submit(By locator) {
            (new WebDriverWait(driver, 10)).until
                (ExpectedConditions.visibilityOfElementLocated(locator));
            driver.findElement(locator).submit();
        }

    public Boolean select(By locator) {
        (new WebDriverWait(driver, 10)).until
                (ExpectedConditions.visibilityOfElementLocated(locator));
        Boolean status = driver.findElement(locator).isSelected();
        return status;
    }

        public WebElement waitForElement (By locator){
            (new WebDriverWait(driver, 10)).until
                    (ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            return element;
        }

        public String getText (By locator){
        (new WebDriverWait(driver, 10)).until
                (ExpectedConditions.visibilityOfElementLocated(locator));
            String text = driver.findElement(locator).getText();
        return text;
        }

        public void pageURL (String url){
            (new WebDriverWait(driver, 20)).until
                    (ExpectedConditions.urlContains(url));
        }


        public void windowHandle(){
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
        }

        public void fileUpload(String filePath) throws IOException, InterruptedException
        {
            Runtime.getRuntime().exec(filePath);
            Thread.sleep(4000);
        }
        public int randomNumber(){
            Random rand = new Random();
            int  num = rand.nextInt(50) + 1;
            return num;
        }
        public String alertgetText()
        {
            return driver.switchTo().alert().getText();

        }
        public void alertAccept()
        {
            driver.switchTo().alert().accept();

        }
        public void typeFileUpload(By locator, String value)
        {
            (new WebDriverWait(driver, 10)).until
                    (ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            element.sendKeys(value);
        }
        public void clickByPresenceofelement(By locator)
        {
            (new WebDriverWait(driver, 10)).until
                    (ExpectedConditions.presenceOfElementLocated(locator));
            driver.findElement(locator).click();
        }
        public String getTextForTextbox(By locator)
        {
            (new WebDriverWait(driver, 10)).until
                    (ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            return element.getText();

        }

    }


