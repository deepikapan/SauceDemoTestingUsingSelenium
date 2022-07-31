package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ProductPageTest {
    public WebDriver driver;
    private BaseClass objBaseClass;
    private LoginPage objLoginPage;
    private ProductPage objProductPage;
    private MyListener objMyListener;
    Boolean status;
    private String text;

    /**
     * This can be checked only after log-in to account with valid credentials. So this
     * class's tests are dependant on loginTest test i.e. launchingApplication .
     */

    public BaseClass getObjBaseTest() {
        return objBaseClass;
    }
    @BeforeClass
    @Parameters({"webDriverName"})
    public void initializeDriver(String driverName) {
        objBaseClass = new BaseClass(driverName);
        driver = objBaseClass.initializeDriver();
        objLoginPage = new LoginPage(driver);
        objProductPage = new ProductPage(driver);
    }

    @AfterClass
    public void tearDown() {
        objBaseClass.tearDown();
    }


    @Test(priority = 1, groups = {"login"})
    @Parameters({"appURL", "userName", "passWord"})
    public void verifyLoginSuccessfulWithValidCredsProductPage(String appURL, String userName, String passWord) {
        System.out.println("UserName is : "+userName);
        System.out.println("Password is :"+passWord);
        objBaseClass.openBrowser(appURL);
        driver.manage().window().maximize();
        objLoginPage.setUserNameFieldLoginPage(userName);
        objLoginPage.setPassWordFieldLoginPage(passWord);
        objLoginPage.clickOnLoginButtonLoginPage();
        status = objProductPage.isProductPageIsDisplayed();
        Assert.assertEquals(status, true);
    }

    @Test(priority = 2)
    public void verifyMenuButtonIsVisibleProductPage() {
        status = objProductPage.IsMenuButtonVisibleOnProductPage();
        Assert.assertEquals(status, true);
    }

    @Test(priority = 3)
    public void verifyAllMenuOptionsListedProductPage() {
        objProductPage.clickMenuButtonThreeLinesProductPage();
        status = objProductPage.IsSideBarMenuOptionsListedProductPage();
        Assert.assertEquals(status, true);
        objProductPage.clickCrossButtonMenuOptionProductPage();
    }

    @Test(priority = 4, testName = "TC- 4", description = "To Test Sort option is showing all options in drop down list")
    public void verifyProductSortContainerIsWorkingProductPage() {
        objProductPage.clickProductSortContainerDropDownProductPage();
        status = objProductPage.isProductSortContainerShowingAllOptionsProductPage();
        Assert.assertEquals(status, true);
    }

    @Test(priority = 5, testName = "TC -5", description = "To Test ProductNAmes are sorted in ascending order after " +
            "selecting A-Z option")
    public void verifySortDropDownShowsProductNamesInDescendingOrderProductPage() {
        objProductPage.clickProductSortContainerDropDownProductPage();
        objProductPage.selectOptionsFromSortDropDownProductPage("Name (Z to A)");
        status = objProductPage.isSelectedOptionCorrectUnderSortDropDownProductPage("Name (Z to A)");
        Assert.assertEquals(status, true);
    }
}

