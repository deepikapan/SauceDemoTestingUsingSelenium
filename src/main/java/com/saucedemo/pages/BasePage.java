package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Creating base class as an abstract class so that an object can not be created.
 * With inheritance we can access this class.
 * Basepages class will be parent class and each page class will be our child class.
 * Since basepage class is parent class, it should contain class member's that must be available on other pages
 * like url, page title and an object that waits for an element.
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected List<WebElement> listOfElementToFind;
    protected WebElement singleElementToFind;
    Boolean status;
    String text;

    /**
     * Creating a constructor and passing webdriver as an argument
     */

    public BasePage(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Defining a method to locate element on our all pages. This is a selenium method.
     */
    protected WebElement findElement(By locator) {
        singleElementToFind = driver.findElement(locator);
        return singleElementToFind;
    }

    /**
     * Creating method to writing something in our pages.
     */
    protected void typeText(String text, By locator) {
        findElement(locator).clear();
        findElement(locator).sendKeys(text);
    }

    /**
     * Creating a method to click. This click method will work on button, link, any other element that is clickable
     * on webpage.
     */
    protected void clickOnElement(By locator) {
        findElement(locator).click();
    }

    /**
     * Click on Element By passing WebElement
     */
    protected void clickOnElementByPassingWebElement(WebElement element) {
        element.click();
    }

    /**
     * Creating a method to check whether element is displayed or not
     */
    protected Boolean isDisplayed(By locator) {
        try {
            status = findElement(locator).isDisplayed();
            return status;
        } catch (NoSuchElementException exp) {
            return false;
        }
    }

    /**
     * Creating a method to check whether element is enabled or not
     */
    protected Boolean isEnabled(By locator) {
        try {
            status = findElement(locator).isEnabled();
            return status;
        } catch (NoSuchElementException exp) {
            return false;
        }
    }

    /**
     * Creating a method to check whether element is Selected or not
     */
    protected Boolean isSelected(By locator) {
        try {
            status = findElement(locator).isSelected();
            return status;
        } catch (NoSuchElementException exp) {
            return false;
        }
    }

    /**
     * Defining a method to locate List of webElement on a webPage.
     */
    protected List<WebElement> findElements(By locator) {
        listOfElementToFind = driver.findElements(locator);
        return listOfElementToFind;
    }

    /**
     * Select Radio Button
     */
    protected void selectRadioButton(By locator) {
        findElement(locator).click();
    }

    /**
     * Select Single CheckBox
     */
    protected void selectSingleCheckBox(By locator) {
        status = isSelected(locator);
        if (status == false) {
            findElement(locator).click();
        } else {
            System.out.println("Checkbox Already selected");
        }

    }

    /**
     * text of Selected CheckBox or radioButton
     */
    protected void textOfSelectedCheckBoxOrRadioButton() {

    }

    /**
     * Select Multiple CheckBox
     */
    protected void selectMultipleCheckBox(By locator) {
    }

    /**
     * element is visible
     */
    protected Boolean isElementVisibleOnPage(By locator) {
        status = findElement(locator).isDisplayed();
        return status;
    }

    /**
     * Actions to perform click using mouse
     */
    protected void mouseHoverAndClick(By locator) {
        WebElement ele = findElement(locator);
        Actions act = new Actions(driver);
        //act.click(ele); (New feature of selenium 4 , click method accepts webelement . This is replacement for
        // moveToElement )
        Action mouseHover = act.moveToElement(ele).click().build();
        mouseHover.perform();
    }

    /**
     * List all options under Drop down menu
     */
    protected List<WebElement> dropDownListAllOptions(By locator) {
        Select drpDwn = new Select(findElement(locator));
        List<WebElement> listOfElement = drpDwn.getOptions();
        return listOfElement;
    }

    public void dropDownSelectionByIndex(WebElement dropDown, int index) {
        Select drpDwn = new Select(dropDown);
        drpDwn.selectByIndex(index);
    }

    public void dropDownSelectionByVisibleText(WebElement dropDown, String text) {
        Select drpDwn = new Select(dropDown);
        drpDwn.selectByVisibleText(text);
        ;
    }

    /**
     * Get selected Text
     */
    public String getTextOfSelectedElement(By locator) {
        text = findElement(locator).getText();
        return text;
    }
    /** Upload a file: Upload a file can be achieve by using sendKeys method. */
    public void enterPathOfFileToUpload(By locator, String filePath){
        singleElementToFind = findElement(locator);
        // Entering the file path in the file-selection path field
        singleElementToFind.sendKeys(filePath);

    }
    /** Handling Alerts :
     * An Alert in selenium is small message box that displays on screen to give user some information or notification.
     * This asks user's permission to perform some tasks , to display certain error or problems .
     * 1- Simple Alert : This has simple Ok button . This shows some information before performing any tasks.
     * 2- Prompt Alert : This asks user to enter some information and then to accept it or user can simple deny this alert.
     * 3- Confirmation Alert : In this user has to check some checkbox before performing any task.
     * */

    /** To Dismiss the Alert or click "Cancel" on alert
     * */
    public void clickCancelOnAlert(By locatorCrossButtonOnAlert, String textOnAlertToCancel){
        text = driver.switchTo().alert().getText();
        if(text == textOnAlertToCancel) {
            driver.switchTo().alert().dismiss();
        }
        else
        {
            findElement(locatorCrossButtonOnAlert).click();
        }
    }

    /** To Accept the Alert or click "Ok"
      */
    public void clickOkOnAlert(By locatorCrossButtonOnAlert, String textOnAlertToAccept){
        text = driver.switchTo().alert().getText();
        if(text == textOnAlertToAccept) {
            driver.switchTo().alert().accept();
        }
        else
        {
            findElement(locatorCrossButtonOnAlert).click();
        }
    }

    /** Send Some data in alert box
     * */
    public void enterDataInAlertBox(String textToEnter){
        driver.switchTo().alert().sendKeys(textToEnter);
        driver.switchTo().alert().accept();
    }

}
