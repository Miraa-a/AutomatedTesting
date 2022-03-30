package com.mycompany.app.core;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.mycompany.app.core.Helpers.*;
import static org.testng.Assert.*;

public class WishlistTester {

    @Step("Opening product")
    public void openProduct(){
        navigate("https://www.dns-shop.ru/product/6feb4fe35c723330/eksn-kamera-aceline-s-40-cernyj/");
    }

    @Step("Removing from wishlist (if exists)")
    public void checkIsAlreadyInWishlist(){
        waitUntilElementAppears(By.cssSelector(".product-buy"));
        waitUntilElementAppears(By.cssSelector(".button-ui.button-ui_white.button-ui_icon.wishlist-btn"));

        System.out.println("Waiting for animation to finish");
        sleep(3);

        if(elementExists(By.cssSelector("button.button-ui.button-ui_white.button-ui_icon.wishlist-btn.button-ui_done")))
        {
            System.out.println("Already on wishlist");
            click(By.cssSelector("button.button-ui.button-ui_white.button-ui_icon.wishlist-btn.button-ui_done"));
            waitUntilElementAppears(By.cssSelector(".button-ui.button-ui_white.button-ui_icon.wishlist-btn:not(.button-ui_done)"));
        }
    }

    @Step("Pressing add to wishlist button")
    public void pressWishlistButton(){
        waitUntilElementAppears(By.cssSelector(".button-ui.button-ui_white.button-ui_icon.wishlist-btn"));
        waitUntilElementAppears(By.cssSelector(".button-ui.button-ui_white.button-ui_icon.wishlist-btn:not(.button-ui_done)"));
        click(By.cssSelector("button.button-ui.button-ui_white.button-ui_icon.wishlist-btn"));
        waitUntilElementAppears(By.cssSelector(".button-ui.button-ui_white.button-ui_icon.wishlist-btn.button-ui_done"));
        sleep(3);
        takeScreenshot("wishlist");
    }

    @Step("Checking if item was added to wishlist")
    public void checkAdded(){
        navigate("https://www.dns-shop.ru/wishlist/index/");

        waitUntilElementAppears(By.cssSelector(".profile-wishlist__tab-content"));
        sleep(2);

        assertTrue(elementExists(By.cssSelector(".catalog-product__name.ui-link.ui-link_black")), "Nothing is on wishlist");
        assertEquals("Экшн-камера Aceline S-40 черный [5 Мп, 1920x1080 30 кадр./сек, угол 120°, подводный бокс, экран, 900 мА*ч]",
                elementText(By.cssSelector(".catalog-product__name.ui-link.ui-link_black")));
    }

    @Step("Removing from wishlist")
    public void pressRemoveButton(){
        waitUntilElementAppears(By.cssSelector(".button-ui.button-ui_white.button-ui_icon.wishlist-btn.button-ui_done"));
        click(By.cssSelector(".button-ui.button-ui_white.button-ui_icon.wishlist-btn.button-ui_done"));
        waitUntilElementAppears(By.cssSelector(".profile-wishlist-management__main-return"));
        assertTrue(elementExists(By.cssSelector(".catalog-product__name.ui-link.ui-link_black")), "Something is on wishlist");
    }

    @Step("This step fails")
    public void failtStep(){
        click(By.linkText("Заказать доставку"));
    }
}
