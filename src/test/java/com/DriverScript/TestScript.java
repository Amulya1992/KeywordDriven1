package com.DriverScript;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.record.FileSharingRecord;

import java.io.File;
import java.io.IOException;
import javax.swing.plaf.FileChooserUI;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.Functionlibrary.CommonFunctions;
import com.utility.ExcelFileUtil;
import com.utility.PropertyFileUtil;

public class TestScript 
{
	public Logger log= Logger.getLogger(TestScript.class);
	public WebDriver driver;
	public ExcelFileUtil excel;
	public void startTest() throws IOException
	{
		PropertyConfigurator.configure("./Logs/log4j.properties");
		excel= new ExcelFileUtil();
		for(int i=1;i<=excel.rowCount("TESTCASES");i++)
		{
			if(excel.getData("testcases", i, 2).equalsIgnoreCase("Y"))
			{
				String moduleStatus="";
				String tcModule = excel.getData("TESTCASES",i,1);
				int rowCount = excel.rowCount(tcModule);
				for(int j=1;j<=rowCount;j++)
				{
					String description = excel.getData(tcModule,j,0);
					String objectType = excel.getData(tcModule, j,1);
					String locatorType = excel.getData(tcModule,j,2);
					String locatorValue = excel.getData(tcModule,j,3);
					String testData = excel.getData(tcModule,j,4);
					try {
						if(objectType.equalsIgnoreCase("startBrowser"))
						{
							driver =CommonFunctions.startBrowser(driver, PropertyFileUtil.getValueForKey("driverPath"), PropertyFileUtil.getValueForKey("Browser"));
							log.info(description);
						}
						if(objectType.equalsIgnoreCase("openApplication"))
						{
							CommonFunctions.openApplication(driver);
							log.info(description);
						}
						
						if(objectType.equalsIgnoreCase("clickAction"))
						{
							CommonFunctions.clickAction(driver, locatorType, locatorValue);
							log.info(description);
						}
						if(objectType.equalsIgnoreCase("typeAction"))
						{
							CommonFunctions.typeAction(driver, locatorType, locatorValue, testData);						
							log.info(description);
						}
						if(objectType.equalsIgnoreCase("waitForElement"))
						{
							CommonFunctions.waitForElement(driver,testData);						
							log.info(description);
						}
						if(objectType.equalsIgnoreCase("closeBrowser"))
						{
							CommonFunctions.closeBrowser(driver);						
							log.info(description);
						}
						
						if(objectType.equalsIgnoreCase("textValidation"))
						{
							CommonFunctions.textValidation(driver,testData);						
							log.info(description);
						}
						excel.setData(tcModule, j, 5,"PASS");
						moduleStatus = "true";
						log.info(description+"PASS");
					} 
					catch (Exception e) 
					{
						excel.setData(tcModule, j, 5,"FAIL");
						moduleStatus="false";
						log.info(description+"FAIL");
						File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						//FileHandler.copy(src,new File("./ScreenShot"+"-"+description+".png"));
						String dst = "E:/amulya/OrangeHR/ScreenShot"+description+" "+CommonFunctions.randomDate()+".png";
						FileUtils.copyFile(src,new File(dst));
						break;
					}
					catch (AssertionError e) 
					{
						excel.setData(tcModule, j, 5,"FAIL");
						moduleStatus="false";
						log.info(description+"FAIL");
						File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//		FileHandler.copy(src,new File("E:/amulya/OrangeHR/ScreenShot"+"-"+description+"/.png"));
						FileUtils.copyFile(src,new File("E:/amulya/OrangeHR/ScreenShot"+"-"+description+""+CommonFunctions.randomDate()+"/.png"));
						break;
					}
					
				}
				if(moduleStatus.equalsIgnoreCase("true"))
				{
					excel.setData("TESTCASES", i,3,"PASS");
				}
				else
				{
					excel.setData("TESTCASES", i,3,"FAIL");
				}
			}
			else
			{
				excel.setData("TESTCASES",i,3,"NOT EXECUTED");
			}
		}
	}
}
