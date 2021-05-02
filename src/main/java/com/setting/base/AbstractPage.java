/**
 * 
 */
package com.setting.base;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author ARIDHIHichem
 *
 */
public abstract class AbstractPage extends TestBasePage{
	
	public AbstractPage(WebDriver driver) throws AWTException {
		super(driver);
	}
	
	
	public abstract List<WebElement> findElements(By locator);
	public abstract WebElement findElement(By locator);
	public abstract void clickOnElement(By locator);
	public abstract void sendText(By locator,String keysToSend);
	public abstract String getText(By locator);
	public abstract String getTiltle(By locator);
	public abstract String getAttribute(By locator,String name);
	public abstract String getCssValue(By locator, String propertyName);
	public abstract void getWindowHandle();
	public abstract void getWindowHandles();
	public abstract void getPageSource(By locator);
	public abstract void javaScriptClick(String argument, String index);
	public abstract void javaScriptSet(String argument, String index , String attribue);
	public abstract void waitAlert();

	
}
