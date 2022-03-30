package com.mycompany.app.core;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;

public class Helpers {

    public static void click(By by){
        Configuration
                .getWebDriver()
                .findElement(by)
                .click();
    }

    public static boolean elementExists(By by){
        return !(Configuration
                .getWebDriver()
                .findElements(by)
                .isEmpty());
    }

    public static void waitUntilElementAppears(By by){
        new WebDriverWait(Configuration.getWebDriver(), Duration.ofSeconds(15))
                .until(webDriver -> !webDriver.findElements(by).isEmpty());
    }

    public static void sendText(String text,By by){
        Configuration.getWebDriver()
                .findElement(by)
                .sendKeys(text);
    }

    public static void navigate(String url){
        Configuration
                .getWebDriver()
                .get(url);
    }

    public static void executeScript(String script){
        ((JavascriptExecutor)Configuration
                .getWebDriver())
                .executeScript(script);
    }

    public static String currentUrl() {
        return Configuration
                .getWebDriver()
                .getCurrentUrl();
    }


    public static String elementText(By by){
        return Configuration
                .getWebDriver()
                .findElement(by)
                .getText();
    }


    public static List<WebElement> getElements(By by){
        return Configuration
                .getWebDriver()
                .findElements(by)
                ;
    }

    public static void sleep(long seconds){
        try{
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public static void takeScreenshot(String name){
        TakesScreenshot scr = (TakesScreenshot) Configuration.getWebDriver();
        Allure.addAttachment(name+".PNG", new ByteArrayInputStream(scr.getScreenshotAs(OutputType.BYTES)));
        System.out.println("Screenshot of the test is taken");
    }
}
