package com.saucedemo.tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyListener implements ITestListener {

    private BaseClass objBaseClass;

    @Override
    public void onTestFailure(ITestResult result) {
        getDriverInstanceAndVerifyStatusCode(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getDriverInstanceAndVerifyStatusCode(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        getDriverInstanceAndVerifyStatusCode(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        getDriverInstanceAndVerifyStatusCode(result);
    }


    private void getDriverInstanceAndVerifyStatusCode(ITestResult result) {
        try {
            //reflection
            Method m = result.getInstance().getClass().getMethod("getObjBaseTest", null);
            objBaseClass = (BaseClass) m.invoke(result.getInstance());
            checkResultStatusCode(result, objBaseClass.getDriver());

        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void checkResultStatusCode(ITestResult result, WebDriver driver) {
        int resultCode = result.getStatus();
        if (resultCode == 2) {
            String testCaseName = captureScreenShot(result, driver);
            System.out.println("Test Case : " + testCaseName + " is failed and ScreenShot is Taken.");
        } else if (resultCode == 3) {
            String msgFromScreenShot = captureScreenShot(result, driver);
            System.out.println("Test Case : " + msgFromScreenShot + " is Skipped and ScreenShot is Taken. ");
        } else if (resultCode == 4) {
            String msgFromScreenShot = captureScreenShot(result, driver);
            System.out.println("Test Case: " + msgFromScreenShot + " is in Success Percentage but failed and screenshot "
                    + " is taken.");
        } else if (resultCode == 16) {
            String msgFromScreenShot = captureScreenShot(result, driver);
            System.out.println("TestCase " + msgFromScreenShot + " is started. : ");
        } else if (resultCode == 1) {
            System.out.println("TestCase : " + result.getName() + " is Passed. ");
        }

    }

    public String captureScreenShot(ITestResult result, WebDriver driver) {
        try {
            TakesScreenshot ts = ((TakesScreenshot) driver);
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
            return result.getName();

        } catch (Exception e) {
            return e.getMessage();

        }
    }

    public String getCurrentSystemDate() {
        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        // get current date time with Date()
        Date currentDate = new Date();

        // In below line , We are formatting the date:
        String date = dateFormat.format(currentDate);
        return date;
    }

}
