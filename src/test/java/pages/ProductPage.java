package pages;

import java.util.function.Supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductPage {
    
    public ChromeDriver web;
    public WebElement product;
    public Supplier<WebElement> productItem;
    public Supplier<String> productTitle;
    public Supplier<String> productPrice;
    public Supplier<String> productDesc;
    public Supplier<String> productImage;
    public Supplier<WebElement> productAddToCartButton;
    public Supplier<String> getProductButtonState;

    public ProductPage(ChromeDriver driver, WebElement product) {
        this.web = driver;
        this.product = product;

        this.productItem = () -> this.product.findElement(By.cssSelector("[data-test=\"inventory-item-name\"]"));
        this.productTitle = () -> this.productItem.get().getText();
        this.productPrice = () -> this.product.findElement(By.cssSelector("[data-test=\"inventory-item-price\"]")).getText();
        this.productDesc = () -> this.product.findElement(By.cssSelector("[data-test=\"inventory-item-desc\"]")).getText();
        this.productImage = () -> this.product.findElement(By.tagName("img")).getAttribute("alt");
        this.productAddToCartButton = () -> this.product.findElement(By.cssSelector("[data-test*=\"add-to-cart\"]"));
        this.getProductButtonState = () -> this.product.findElement(By.tagName("button")).getText();
    }

    public void addToCart() {
        this.productAddToCartButton.get().click();
    }
}
