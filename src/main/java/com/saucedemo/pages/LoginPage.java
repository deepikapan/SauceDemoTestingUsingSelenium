package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This Login page extends our basepage class. because we have created all common methods and variables in basePage
 * class and using inheritance we can access those fields.
 */

public class LoginPage extends BasePage {

    /**
     * I have created the fields private so only methods in this LoginPage class have access to these fields.
     * By is a class for locating elements and value is going to be locator .
     */
    private Boolean status;
    private String text;
    private By userNameField = By.id("user-name");
    private By passWordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMsgInvalidCredential = By.xpath("//div[@class='error-message-container error']//h3[contains(text(), 'Username and password do not match')]");

    /**
     * We are calling super then passing in the driver to load the constructor from our BasePage.
     * (Go and check basePage class if any confusion.)
     * The constructor is created to bring in the WebDriver so the driver can access the browser.
     */

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /** To perform actions on above elements , we need to have some methods that can interact with these elements.
     * There are 4 methods that we can use in our page object model.
     * 1- getters and setters methods: it is encapsulation. The getter method gets the state of the application
     * and setter method helps us to create a test that interacts with our application.
     * 2- Transition methods: This method is important when our application changes to a different page. It may change
     * when we click on button, link etc.
     * 3- Convenience method: It is created when we combining more than one methods into single method.
     */

    /** Setting userName for application using typeText method of BasePage class.
     *
     */

    /**
     * Setting userName for application using typeText method of BasePage class.
     *
     * @param userName
     */
    public void setUserNameFieldLoginPage(String userName) {
        typeText(userName, userNameField);
    }

    public void setPassWordFieldLoginPage(String passWord) {
        typeText(passWord, passWordField);
    }

    public String getUserNameFieldLoginPage() {
        text = findElement(userNameField).getAttribute("value");
        return text;
    }

    public By getPassWordFieldLoginPage() {
        return passWordField;
    }

    /**
     * When we click on Login button on Login page then the login page transitions into product page, thats why we are
     * using transition method.
     * In this, we are adding a return type of "ProductPage" class, as we are transitioning into ProductPage
     * after clicking login button.
     */
    public ProductPage clickOnLoginButtonLoginPage() {
        clickOnElement(loginButton);
        return new ProductPage(driver);
    }

    /**
     * Below method is convenience method: One method to login into account that includes set username, password,
     * and click on login button. Seperate method is also required as sometimes we need to perform negative test with
     * blank password or blank username etc.
     */
    public ProductPage loginInOneShotUSingUserNamePasswordLoginPage(String userName, String passWord) {
        setUserNameFieldLoginPage(userName);
        setPassWordFieldLoginPage(passWord);
        return clickOnLoginButtonLoginPage();

    }
    /** Checking if error msg is being displayed while entering wrong login credentials.
     */
    public Boolean isLoginErrorMessageIsDisplayedLoginPage(){
        status = isDisplayed(errorMsgInvalidCredential);
        System.out.println("Status of Error msg is : " +status);
        return status;
    }
    /** Login Button is Displayed */
    public Boolean isLoginButtonDisplayedLoginPage(){
       status= isDisplayed(loginButton);
       return status;
    }
}
