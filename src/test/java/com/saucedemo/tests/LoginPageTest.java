package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTest {
    private WebDriver driver;
    private BaseClass objBaseClass;
    private LoginPage objLoginPage;
    private ProductPage objProductPage;
    Boolean status;

    public BaseClass getObjBaseTest() {
        return objBaseClass;
    }
    /**
     * Initializing Driver where I am getting DriverName from TestNG.xml
     * Initializing every objects inside this method as driver is initializing inside this.
     */
    @BeforeClass
    @Parameters({"webDriverName"})
    public void initializeDriver(String driverName) {
        objBaseClass = new BaseClass(driverName);
        driver = objBaseClass.initializeDriver();
        objLoginPage = new LoginPage(driver);
        objProductPage = new ProductPage(driver);
    }

    @Test(priority = 1, groups = {"login"})
    @Parameters({"appURL"})
    public void launchingApplication(String appURL) {
        objBaseClass.openBrowser(appURL);
    }

    /**
     * Getting userName from TestNG.xml
     */
    @Test(priority = 2, groups = {"login"})
    @Parameters({"userName", "passWord"})
    public void setUserNameAndPassWord(String userName, String passWord) {
        System.out.println("username is : " + userName);
        System.out.println("Password is : " + passWord);
        objLoginPage.setUserNameFieldLoginPage(userName);
        objLoginPage.setPassWordFieldLoginPage(passWord);
        objLoginPage.clickOnLoginButtonLoginPage();
        status = objProductPage.isProductPageIsDisplayed();
        System.out.println("Status of shopping cart image is " + status);
        Assert.assertEquals(status, true);
        objProductPage.clickMenuButtonThreeLinesProductPage();
        objProductPage.clickLogoutLinkProductPage();
        status = objLoginPage.isLoginButtonDisplayedLoginPage();
        Assert.assertEquals(status, false);
    }

    /**
     * Negative Testing with invalid username and valid password
     */
    @Test(priority = 3)
    @Parameters({"InvalidUserName", "ValidPassword"})
    public void loginFailedWithInvalidUserNameAndValidPassword(String InvalidUserName, String ValidPassword) {

        objLoginPage.setUserNameFieldLoginPage(InvalidUserName);
        objLoginPage.setPassWordFieldLoginPage(ValidPassword);
        objLoginPage.clickOnLoginButtonLoginPage();
        status = objLoginPage.isLoginErrorMessageIsDisplayedLoginPage();
        Assert.assertEquals(status, true);

    }

    /**
     * Negative Testing with Valid username and Invalid password
     */
    @Test(priority = 4)
    @Parameters({"userName", "InValidPassword"})
    public void loginFailedWithValidUserNameAndInValidPassword(String validUserName, String inValidPassword) {
        objLoginPage.setUserNameFieldLoginPage(validUserName);
        objLoginPage.setPassWordFieldLoginPage(inValidPassword);
        objLoginPage.clickOnLoginButtonLoginPage();
        status = objLoginPage.isLoginErrorMessageIsDisplayedLoginPage();
        Assert.assertEquals(status, true);
    }
}