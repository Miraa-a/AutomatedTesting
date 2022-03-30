package com.mycompany.app.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Configuration {
    private Configuration(){}

    static WebDriver webDriver;
    public static WebDriver getWebDriver(){
        return webDriver;
    }

    public static void init(String driverPath){
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        new WebDriverWait(webDriver, Duration.ofSeconds(20)).
                until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        webDriver.manage().deleteAllCookies();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public static void clear(){
        if(webDriver != null){
            webDriver.close();
        }
    }
}
