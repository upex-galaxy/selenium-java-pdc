package e2e.products;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import e2e.fixture.WebDrivers;
import pages.ProductPage;

public class ProductDetailsTest {

    public ChromeDriver web;

    @BeforeEach
    public void setUp(){
        WebDrivers drivers = new WebDrivers();
        web = drivers.getChromeDriver();
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
    }

    @AfterEach
    public void shutdown(){
        web.quit();
    }
    
    @Test
    public void ShouldProductDetailsBeVisible() throws InterruptedException, IOException{
        List<WebElement> products = web.findElements(By.cssSelector("[data-test=\"inventory-item\"]"));
        Integer randomIndex = (int) (Math.random() * (products.size() - 1));

        WebElement randomProduct = products.get(randomIndex);

        ProductPage givenProduct = new ProductPage(web, randomProduct);
        // Expected Details when the product is opened in details:
        String expectedProductPrice = givenProduct.productPrice.get();
        String expectedProductTitle = givenProduct.productTitle.get();
        String expectedProductDesc = givenProduct.productDesc.get();
        String expectedProductImage = givenProduct.productImage.get();
        String expectedProductButtonState = givenProduct.getProductButtonState.get();

        givenProduct.productItem.get().click(); // go to product details

        String actualUrl = web.getCurrentUrl();
        Assertions.assertTrue(actualUrl.contains("inventory-item.html?id"));

        // Actual Details values:
        WebElement actualProduct = web.findElement(By.cssSelector("[data-test=\"inventory-item\"]"));
        ProductPage actualProductCard = new ProductPage(web, actualProduct);
        String actualProductPrice = actualProductCard.productPrice.get();
        String actualProductTitle = actualProductCard.productTitle.get();
        String actualProductDesc = actualProductCard.productDesc.get();
        String actualProductImage = actualProductCard.productImage.get();
        String actualProductButtonState = actualProductCard.getProductButtonState.get();

        // Assertions for the Details should be the Same:
        Assertions.assertEquals(actualProductPrice, expectedProductPrice);
        Assertions.assertEquals(actualProductTitle, expectedProductTitle);
        Assertions.assertEquals(actualProductDesc, expectedProductDesc);
        Assertions.assertEquals(actualProductImage, expectedProductImage);
        Assertions.assertEquals(actualProductButtonState, expectedProductButtonState);


        System.out.println("🧪 TEST PASSED: TC1: Should Product keep the same details when Product is in Details Page");
    }
}
