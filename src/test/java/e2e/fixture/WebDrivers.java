package e2e.fixture;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDrivers {
    
    public ChromeDriver getChromeDriver(){
        ChromeOptions options = new ChromeOptions();
        String headlessValue = System.getProperty("headless");
        if (headlessValue != null && "true".equalsIgnoreCase(headlessValue)) {
            options.addArguments("--headless");
        }
        ChromeDriver web = new ChromeDriver(options);
        return web;
    }
}
