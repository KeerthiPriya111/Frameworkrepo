package com.comcast.crm.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class webDriver_Utility {
public void waitforpagetoload(WebDriver driver) {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
}
public void waitforElementPresent(WebDriver driver,WebElement elemen) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(elemen));
}
public void switchtonewtab(WebDriver driver,String partialurl) throws InterruptedException {
	Set<String> childWindows=driver.getWindowHandles();	
	  for(String window: childWindows)
	  {
		  driver.switchTo().window(window);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    		 
		  if(driver.getCurrentUrl().equals(partialurl))
		  {
		  break;
		  }
	  }
//	Set<String> set =driver.getWindowHandles();
//	Iterator<String> it = set.iterator();
//	while(it.hasNext()) {
//		String windowID = it.next();
//		driver.switchTo().window(windowID);
//		String actUrl = driver.getCurrentUrl();
//		if(actUrl.contains(partialTitle)) {
//			break;
//		}
//		
//	  }
}

public void switchtoFrame(WebDriver driver,int index) {
	driver.switchTo().frame(index);
}

public void switchtoFrame(WebDriver driver,String name) {
	driver.switchTo().frame(name);
}

public void switchtoFrame(WebDriver driver,WebElement element) {
	driver.switchTo().frame(element);
}

public void switchtoAlert(WebDriver driver) {
	driver.switchTo().alert();
}

public void switchtoAlertandAssert(WebDriver driver) {
	driver.switchTo().alert().accept();;
}
public void switchtoAlertDismiss(WebDriver driver) {
	driver.switchTo().alert().dismiss();;
}

public void select(WebElement element,String text) {
	Select sel = new Select(element);
	sel.selectByVisibleText(text);
}

public void select(WebElement element,int index) {
	Select sel = new Select(element);
	sel.selectByIndex(index);
}
public void mousecontextclick(WebElement element,WebDriver driver) {
	Actions act = new Actions(driver);
	act.contextClick(element).perform();;	
}
public void mousemoveonelement(WebElement element,WebDriver driver) {
	Actions act = new Actions(driver);
	act.moveToElement(element).perform();
}
public void mousedblclick(WebElement element,WebDriver driver) {
	Actions act = new Actions(driver);
	act.doubleClick(element).perform();	
}




}
