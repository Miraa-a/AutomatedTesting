package com.mycompany.app;
import com.mycompany.app.core.*;

import com.mycompany.app.core.Configuration;

import org.testng.annotations.*;

/**
 * Unit test for simple App.
 */
@Listeners({ScreenshotTestListener.class})
public class AppTest
{
    private final String driverPath = "chromedriver.exe";

    @BeforeTest
    public void profileSetup() {
        System.out.println("Test setup process is completed");
    }

    @BeforeClass
    public void appSetup() {
        Configuration.init(driverPath);
        System.out.println("The app setup process is completed");
    }

    @DataProvider(name = "minPrice")
    public Object[][] getMinPrice(){ return  new Object[][] {{10000}, {20000} }; }

    @Test(priority = 1)
    public void profile(){
        ProfileTester profile = new ProfileTester();
        profile.openAddress();
        profile.clickSignInButton();
        profile.setPasswordAuthMode();
        profile.setCredentials();
        profile.pressLogin();
        profile.checkLogin();
    }

    @Test(priority = 2)
    public void search(){
        SearchTester searchTester = new SearchTester();
        searchTester.openSearchPage();
        searchTester.setSearchText();
        searchTester.findItemInList();
        searchTester.chooseItemInList();
        searchTester.checkItemPage();
    }

    @Test(priority = 3)
    public void wishlist(){
        WishlistTester wishlistTester = new WishlistTester();
        wishlistTester.openProduct();
        wishlistTester.checkIsAlreadyInWishlist();
        wishlistTester.pressWishlistButton();
        wishlistTester.checkAdded();
        wishlistTester.pressRemoveButton();
        wishlistTester.failtStep();
    }

    @Test(priority = 4, dataProvider = "minPrice")
    public void filterTester(int minPrice){
        FilterTester filterTester = new FilterTester();
        filterTester.openFilterPage();
        filterTester.chooseCategories();
        filterTester.setOrder();
        filterTester.setPrice(minPrice);
        filterTester.applyFilters();
        filterTester.checkPrices(minPrice);
    }


    @AfterClass
    public void closeUp() {
        Configuration.clear();
        System.out.println("The close up process is completed");
    }

    @AfterSuite
    public void cleanUp() {
        System.out.println("All close up activities completed");
    }


}
