package com.mycompany.app.core;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.mycompany.app.core.Helpers.*;
import static org.testng.Assert.assertTrue;

public class ProfileTester {

    @Step("Opening main page")
    public void openAddress() {
        navigate("https://www.dns-shop.ru/");
    }

    @Step("Click on sign in btn")
    public void clickSignInButton(){
        waitUntilElementAppears(By.cssSelector(".button-ui.button-ui_white.header__login_button"));
        click(By.cssSelector(".button-ui.button-ui_white.header__login_button"));
    }

    @Step("Setting password auth mode")
    public void setPasswordAuthMode(){
        waitUntilElementAppears(By.cssSelector(".base-button-container.base-button-container_blue"));
        click(By.cssSelector(".base-button-container.base-button-container_blue"));
    }

    @Step("Entering credentials")
    public void setCredentials(){
        waitUntilElementAppears(By.xpath("//div[@class ='form-entry-with-password__title' and text() = 'Войти c паролем']"));
        sendText("liraniy414@f1xm.com", By.cssSelector(".form-entry-with-password input[autocomplete=\"username\"]"));
        sendText("5LPDGNyEEtVNubT", By.cssSelector(".form-entry-with-password input[autocomplete=\"current-password\"]"));
    }

    @Step("Clicking on login")
    public void pressLogin(){
        waitUntilElementAppears(By.cssSelector(".form-entry-with-password .base-ui-button"));
        click(By.cssSelector(".form-entry-with-password .base-ui-button"));
    }

    @Step("Checking if is signed in")
    public void checkLogin(){
        waitUntilElementAppears(By.cssSelector(".header-profile__level"));
        System.out.println("Waiting for login");

        assertTrue(elementExists(By.cssSelector(".header-profile__level")), "Login failed");
        System.out.println("The login process on dns-shop.ru is completed");
    }
}
