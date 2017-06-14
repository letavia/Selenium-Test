import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Created by Leny Oktavia on 6/8/2017.
 */

    public class Google {


    @Test
    public void testLogin() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Git\\Test-Hudi\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        ChromeDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://drive.google.com");
        driver.manage().window().maximize();

        try {
            driver.findElement(By.linkText("Go to Google Drive")).click();
            driver.findElement(By.id("identifierId")).sendKeys("thelenyoktavia@gmail.com");
            driver.findElement(By.id("identifierNext")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
            driver.findElement(By.name("password")).sendKeys("Sp!d3rn0v4");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.id("passwordNext")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("drive_main_page")));
        } catch(Exception e){
            fail(e.getMessage());
        } finally{
            driver.quit();
        }
    }

    @Test
    public void testForm() throws InterruptedException {






    }





}