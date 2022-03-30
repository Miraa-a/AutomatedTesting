package com.mycompany.app.core;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.mycompany.app.core.Helpers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTester {

    @Step("Opening search page")
    public void openSearchPage(){
        navigate("https://www.dns-shop.ru/");
        waitUntilElementAppears(By.xpath("//a[contains(@class, 'menu-desktop__root-subtitle') and text()=\"фототехника\"]"));
        click(By.xpath("//a[contains(@class, 'menu-desktop__root-subtitle') and text()=\"фототехника\"]"));
    }

    @Step("Entering search text")
    public void setSearchText(){
        String searchText = "Aceline S-40";
        executeScript("(()=> { let element = document.querySelector('.ui-input-search__input.ui-input-search__input_presearch'); element.value = '"+searchText+"'; element.form.submit(); })()");
    }

    @Step("Finding items from list")
    public void findItemInList(){
        waitUntilElementAppears(By.cssSelector(".products-page__list"));
        assertEquals(currentUrl(), "https://www.dns-shop.ru/search/?q=Aceline+S-40", "Search url did not matched");
    }

    @Step("Clicking on item")
    public void chooseItemInList(){
        click(By.partialLinkText("Экшн-камера Aceline S-40 черный"));
    }

    @Step("Checking item page transition")
    public void checkItemPage(){
        waitUntilElementAppears(By.cssSelector(".product-card-top__title"));
        assertEquals("Экшн-камера Aceline S-40 черный",
                elementText(By.cssSelector(".product-card-top__title")),
                "Wrong product");
    }

}
