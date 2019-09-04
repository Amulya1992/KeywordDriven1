package com.Functionlibrary;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.utility.PropertyFileUtil;

import junit.framework.Assert;

public class CommonFunctions {

	
	public static WebDriver startBrowser(WebDriver driver,String driverPath,String browser)
	{
			if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",driverPath);
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.firefox.driver",driverPath);
				driver = new FirefoxDriver();
			}
			return driver;
	}
	
	public static void openApplication(WebDriver driver) throws IOException
	{
		driver.get(PropertyFileUtil.getValueForKey("URL"));
		driver.manage().window().maximize();
	}
	
	public static void clickAction(WebDriver driver,String locatorType,String LocatorValue)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).click();
		}
		else if(locatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).click();
		}
	}
	
	public static void typeAction(WebDriver driver,String locatorType,String locatorValue,String testdata)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).sendKeys(testdata);
		}
	}
	
	public static void waitForElement(WebDriver driver,String waitTime)
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	
	public static void textValidation(WebDriver driver, String validData)
	{
		String actual_Text = driver.findElement(By.id("welcome")).getText();
		String exp_Text =validData;
		Assert.assertEquals(exp_Text, actual_Text, "Login Failed");
	}
	
	public static String randomDate()
	{
		  Date objDate = new Date(); // Current System Date and time is assigned to objDate
		  System.out.println(objDate);
		  String strDateFormat = "hh:mm:ss a dd-MMM-yyyy"; //Date format is Specified
		  SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); //Date format string is passed as an argument to the Date format object
		  System.out.println(objSDF.format(objDate));
		  return objSDF.format(objDate); 
	}
	}
