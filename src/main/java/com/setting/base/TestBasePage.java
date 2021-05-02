/**
 * 
 */
package com.setting.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * @author ARIDHIHichem
 *
 */
public class TestBasePage {
	
	public  WebDriver driver = null;
	public  Actions builder = null;	
	public  Properties prop;
	public  Robot robot;
	public  FluentWait<WebDriver> wait;
	private int PAGE_LOAD_TIMEOUT = 60 ;
	private int IMPLICIT_WAIT = 2;
	public String propFile = ".\\src\\config\\config.properties" ;
	
	
	public TestBasePage(WebDriver driver) throws AWTException {
        this.driver = driver ;
		this.wait = new WebDriverWait(driver, 200).ignoring(NoSuchElementException.class).ignoring(NullPointerException.class);
		this.builder = new Actions(driver);
		this.robot = new Robot();
		try {
			prop = new Properties();
			File file = new File(propFile);
			FileInputStream target = new FileInputStream(file);
			prop.load(target);
		}catch (Exception e) {
			System.out.println("Properties file not found !");
		}
	}

@BeforeSuite
	public void initialization() throws InterruptedException {
		String browser = prop.getProperty("browser");
		
		if(browser.equals("chrome")) {
			System.setProperty("WebDriver.chrome.driver", prop.getProperty("chrome"));		
		}
		else if(browser.equals("firefox")) {
			System.setProperty("WebDriver.firefox.driver", prop.getProperty("firefox"));			
		}
		else if(browser.equals("edge")) {
			System.setProperty("WebDriver.edge.driver", prop.getProperty("edge"));
		}
		else if(browser.equals("opera")){
			System.setProperty("WebDriver.opera.driver", prop.getProperty("opera"));
		}
		
		else {
			System.out.println("No browser define");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("URL"));
		driver.manage().timeouts().wait(PAGE_LOAD_TIMEOUT);  
	}
@AfterSuite
   public void tearDown() {
	    driver.quit();
}

@AfterMethod(alwaysRun = true)
public void beforeMethod(Method test, ITestResult testResult) {

    //get name of test. if testName annotation is empty get method name.
    String name = test.getName().toString().replace("_", " ");
    String description = test.getDeclaredAnnotation(org.testng.annotations.Test.class).description();

    //get test result
    String result = "UNKNOWN";
    switch (testResult.getStatus()) {
        case 1:
            result = "\u001B[32mSUCCESS";
            break;
        case 2:
            result = "\u001B[31mFAILURE";
            break;
        case 3:
            result = "\u001B[33mSKIP";
            break;
        case 4:
            result = "SUCCESS_PERCENTAGE_FAILURE";
            break;

    }

    System.out.println(String.format("%s|%s|%s", "\u001B[37m"+name, "\u001B[37m"+description, result+"\u001B[0m\n                             *** \n                             ***"));
}

}
