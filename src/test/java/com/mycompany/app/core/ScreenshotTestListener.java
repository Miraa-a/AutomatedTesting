package com.mycompany.app.core;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayInputStream;

public class ScreenshotTestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result){
        TakesScreenshot scr = (TakesScreenshot) Configuration.getWebDriver();
        Allure.addAttachment(result.getName()+".PNG", new ByteArrayInputStream(scr.getScreenshotAs(OutputType.BYTES)));
        System.out.println("Screenshot of the test is taken");
    }
}
