package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductPage extends BasePage {
    Boolean status;
    By swagLogoOnProductPage = By.className("app_logo");
    By shoppingCartImageProductPage = By.id("shopping_cart_container");
    By menuButtonThreeLinesProductPage = By.xpath("//button[@id='react-burger-menu-btn' and text()='Open Menu']");
    By logOutOptionLinkProductPage = By.id("logout_sidebar_link");
    By menuButtonSidebarOptionProductPage = By.xpath("//div[@class= 'bm-menu']//a[contains(@id, 'sidebar_link')]");
    By crossButtonMenuOptionProductPage = By.id("react-burger-cross-btn");
    By allItemOptionMenuButtonProductPage = By.id("inventory_sidebar_link");
    By drpDwnProductSortContainerProductPage = By.xpath("//select[@class='product_sort_container']");
    By inventoryItemPriceProductPage = By.xpath("//div[@class='inventory_item_price']");
    WebElement element;
    List<WebElement> listOfElement;

    public ProductPage(WebDriver driver) {

        super(driver);
    }

    /**
     * Verify productPage is Opened.
     */

    public Boolean isProductPageIsDisplayed() {
        status = isDisplayed(swagLogoOnProductPage);
        return status;
    }

    /**
     * Click on menu button displayed on product page at left hand side top corner.
     */
    public void clickMenuButtonThreeLinesProductPage() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clickOnElement(menuButtonThreeLinesProductPage);
    }

    /**
     * Click on Logout Link of menu
     */
    public void clickLogoutLinkProductPage() {
        clickOnElement(logOutOptionLinkProductPage);
    }

    /**
     * Is Menu button clickable on Product Page
     * Author : Deepika Panwar
     */
    public Boolean IsMenuButtonVisibleOnProductPage() {
        status = isElementVisibleOnPage(menuButtonThreeLinesProductPage);
        return status;
    }

    /**
     * check if all options listed under side bar menu button is listed or not after clicking menu button
     */
    public Boolean IsSideBarMenuOptionsListedProductPage() {
        List<WebElement> menuOptions = findElements(menuButtonSidebarOptionProductPage);
        int listSize = menuOptions.size();
        for (int i = 0; i < listSize; i++) {
            WebElement listedOption = menuOptions.get(i);
            status = listedOption.isDisplayed();
            // String textOfMenuOption = listedOption.getText();
            int x = i + 1;
        }
        return status;
    }

    /**
     * Click cross Button of Menu options
     */
    public void clickCrossButtonMenuOptionProductPage() {
        element = findElement(crossButtonMenuOptionProductPage);
       // clickOnElement(crossButtonMenuOptionProductPage);
        mouseHoverAndClick(crossButtonMenuOptionProductPage);

    }
    /**
     * Click on "All Items" options under Menu button
     */
    public void clickAllItemsMenuButtonProductPage() {
        clickOnElement(allItemOptionMenuButtonProductPage);
    }

    public void clickProductSortContainerDropDownProductPage() {
        clickOnElement(drpDwnProductSortContainerProductPage);
    }

    public Boolean isProductSortContainerShowingAllOptionsProductPage() {
        listOfElement = dropDownListAllOptions(drpDwnProductSortContainerProductPage);
        int sizeOfList = listOfElement.size();
        for (int i = 0; i < sizeOfList; i++) {
            WebElement element = listOfElement.get(i);
            status = element.isDisplayed();
            String text = element.getText();
            int x = i + 1;
        }
        return status;
    }

    /**
     * Select Option from sorting drop Down on Product Page
     */
    public void selectOptionsFromSortDropDownProductPage(String textToSelect) {
        element = findElement(drpDwnProductSortContainerProductPage);
        dropDownSelectionByVisibleText(element, textToSelect);

    }

    /**
     * bvb
     */
    public String getTextSortContainerProductPage() {
        text = getTextOfSelectedElement(drpDwnProductSortContainerProductPage);
        return text;
    }

    public Boolean isSelectedOptionCorrectUnderSortDropDownProductPage(String ExpectedOutput) {
        text = getTextSortContainerProductPage();
        if (text.equalsIgnoreCase(ExpectedOutput)) {

            status = true;
        }
        return status;
    }
}
