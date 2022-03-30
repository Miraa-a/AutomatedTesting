package com.mycompany.app.core;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.mycompany.app.core.Helpers.*;
import static org.testng.Assert.*;

public class FilterTester {

    @Step("Open filter page")
    public void openFilterPage(){
        navigate("https://www.dns-shop.ru/");
        waitUntilElementAppears(By.linkText("Компьютеры"));
        click(By.linkText("Компьютеры"));
    }

    @Step("Choose categories")
    public void chooseCategories(){
        waitUntilElementAppears(By.cssSelector(".breadcrumb-list__item:nth-child(2)"));
        click(By.cssSelector("a.subcategory__item.ui-link.ui-link_blue"));
        waitUntilElementAppears(By.cssSelector(".breadcrumb-list__item:nth-child(3)"));
        click(By.cssSelector("a.subcategory__item.ui-link.ui-link_blue"));
        waitUntilElementAppears(By.cssSelector(".catalog-product__image"));
    }

    @Step("Setting up: order")
    public void setOrder(){
        click(By.cssSelector("div[data-id=\"order\"] a"));
        waitUntilElementAppears(By.cssSelector(".popover-block.popover-block_show"));
        click(By.xpath("//span[contains(@class, 'ui-radio__content') and text() = \"Сначала недорогие\"]"));
    }

    @Step("Setting up: price")
    public void setPrice(int minPrice){
        sendText(minPrice+"", By.cssSelector("div[data-id=\"price\"] input"));
    }

    @Step("Applying filters")
    public void applyFilters(){
        click(By.cssSelector("button.button-ui.button-ui_brand.left-filters__button:first-child"));
    }

    @Step("Checking price filters")
    public void checkPrices(int minPrice){
        sleep(3);
        for (WebElement element :
                getElements(By.cssSelector(".product-buy__price"))) {
            String text = element.getText().replaceAll("[^\\\\.0123456789]","");
            int num = Integer.parseInt(text);
            assertTrue(num >= minPrice, "Фильтр не сработал");
        }
    }
}
