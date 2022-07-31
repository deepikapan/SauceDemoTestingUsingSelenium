package com.saucedemo.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;

public class BaseClass {
    private WebDriver driver;
    private String driverName;

    public BaseClass(String driver) {
        driverName = driver;

    }
    public BaseClass(){

    }
    /**
     * Initializing WebDriver
     */
    protected WebDriver initializeDriver() {
        if (driverName.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Driver-Selenium\\chromedriver103.exe");
            driver = new ChromeDriver();
        }
        else if (driverName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Driver-Selenium\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        return driver;
    }
    protected WebDriver getDriver(){
        return driver;
    }
    /** Launching the application
     */
    protected void openBrowser(String appURL){
        driver.get(appURL);
        driver.manage().window().maximize();
    }

    protected void tearDown() {
        //quitting driver

        driver.quit();

    }

    public void getScreenShot() {
        ITestResult result;

    }
}
