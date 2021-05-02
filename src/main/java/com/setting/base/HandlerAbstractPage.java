/**
 * 
 */
package com.setting.base;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author ARIDHIHichem
 *
 */
public class HandlerAbstractPage extends AbstractPage{
	JavascriptExecutor js = (JavascriptExecutor)driver;
	
	public HandlerAbstractPage(WebDriver driver) throws AWTException {
		super(driver);
		this.js = (JavascriptExecutor)driver;
	}

	@Override
	public List<WebElement> findElements(By locator) {

        List <WebElement> ele = driver.findElements( locator);
		return ele;
	}
	
	@Override
	public WebElement findElement(By locator) {
        WebElement ele = null ;
        if(findElement(locator) !=null && !findElements(locator).isEmpty()){
          try {
          ele = driver.findElement(locator);
          }catch (Exception e) {
          findElements(locator).get(0);
		  }
        } 
        else {
           System.out.println("Element Not Found In Dom");
        }
		return ele;
	}

	@Override
	public void clickOnElement(By locator) {
		 WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(findElement(locator)));
         try {
         findElement(locator).click();
         }catch(Exception e) {
           try {
           builder.moveToElement(ele).pause(500).click().perform();
           }catch(Exception i) {
            System.out.println("Element Not Clickable");	 
           }
         }
	}
	
	@Override
	public void javaScriptClick(String argument ,String index) {		
		js.executeScript("document.getElementBy"+argument+index+".click();");
	}

	@Override
	public void javaScriptSet(String argument, String index , String attribue) {
		js.executeScript("document.getElements"+argument+index+".setAttribute("+attribue+")");		
	}
	
	@Override
	public void sendText(By locator ,String keysToSend) {
        findElement(locator).sendKeys(keysToSend);
        }

	@Override
	public String getText(By locator) {
		String text = findElement(locator).getText();
		return text;
	}

	@Override
	public String getTiltle(By locator) {
        String title = driver.getTitle();
        return title;
	}

	@Override
	public String getAttribute(By locator , String name) {
		String attribute = findElement(locator).getAttribute(name);		
		return attribute;
	}

	@Override
	public void getPageSource(By locator) {
    findElement(locator).getClass();
	}
	
	@Override
	public String getCssValue(By locator, String propertyName) {
       String cssValue = findElement(locator).getCssValue(propertyName);
		return cssValue;
	}
	
	@Override
	public void waitAlert() {
		try {
			WebDriverWait waiter = new WebDriverWait(driver, 1);
			if(waiter.until(ExpectedConditions.alertIsPresent())!=null)
			driver.switchTo().alert().accept();
			}
		    catch (Exception e) {
		    System.out.println("No Alert PopUp Present");		     
		    }	
	 }
	
	@Override
	public void getWindowHandle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getWindowHandles() {
		// TODO Auto-generated method stub
		
	}


	
	

}
