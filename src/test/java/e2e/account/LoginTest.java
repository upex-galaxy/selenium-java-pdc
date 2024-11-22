package e2e.account;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    @Test
    public void TC1LoginTest(){
        ChromeDriver web = new ChromeDriver();
        web.manage().window().maximize();
        web.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        web.get("https://saucedemo.com");

        WebElement usernameInput = web.findElement(By.cssSelector("input[data-test='username']"));
        usernameInput.sendKeys("standard_user");

        WebElement passwordInput = web.findElement(By.cssSelector("input[data-test='password']"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = web.findElement(By.id("login-button"));
        loginButton.click();

        WebElement productList = web.findElement(By.cssSelector("[data-test='inventory-list']"));
        Boolean isProductListVisible = productList.isDisplayed();

        Assertions.assertTrue(isProductListVisible);

        web.quit();
        System.out.println("🧪 TEST PASSED: TC1 Login Test");
    }
}