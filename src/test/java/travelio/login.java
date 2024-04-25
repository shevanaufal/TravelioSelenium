package travelio;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class login {
    @Test
    public void success_login_case() {
        WebDriver driver;
        String baseURL = "https://www.travelio.com/";

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);

        //Go to travelio website and close the modal shown
        try{
            WebDriverWait wait;
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement modalBody = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.modal-body"))
            );

            WebElement closeModal = modalBody.findElement(By.cssSelector("i[data-dismiss='modal'][class='fa fa-close fa-lg close padding15']"));
            closeModal.click();
        } catch (TimeoutException e){
            System.out.println("Modal not found");
        } finally {
            String nonloginpageAssert = driver.findElement(By.xpath("//div[@id=\"loginBtn\"]")).getText();
            Assert.assertEquals(nonloginpageAssert,"Masuk");
        }

        //Click "Masuk" button in navigation bar
        driver.findElement(By.xpath("//div[@id=\"loginBtn\"]")).click();

        //Input Email but need to modal fullyload
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("shevanaufalrifqi28@gmail.com");
//        driver.findElement(By.id("login-email")).sendKeys("shevanaufalrifqi28@gmail.com");

        //Input Password
        driver.findElement(By.xpath("//input[@id='login-password']")).sendKeys("TravelioIgnite99");

        //Click "Masuk" button after fill the data
        driver.findElement(By.xpath("//button[@id=\"login-modal-btn\"]")).click();

        //Verify if already login
        String loggedIn = driver.findElement(By.xpath("//span[contains(text(), 'Sheva')]")).getText();
        Assert.assertEquals(loggedIn, "Sheva");


    }
}
