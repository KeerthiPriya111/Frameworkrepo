package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.webDriver_Utility;

public class Contactwith_org {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		JavaUtility jlib = new JavaUtility();
		webDriver_Utility wlib = new webDriver_Utility();
		FileInputStream fis = new FileInputStream("./configAppData/CD.properties");
	       Properties pro = new Properties();
	       pro.load(fis);
	       
	       String Browser = pro.getProperty("browser");
	       String URL = pro.getProperty("url");
	       String USERNAME = pro.getProperty("username");
	       String PASSWORD = pro.getProperty("password");
	       WebDriver driver=null;
			  if(Browser.equals("chrome"))
			  {
				  driver=new ChromeDriver();
			  }
			  else if(Browser.equals("firefox"))
			  {
				  driver=new FirefoxDriver();
			  }
			  else if(Browser.equals("edge"))
			  {
				  driver=new EdgeDriver();
			  }
			  else
			  {
				  driver=new ChromeDriver();
			  }
	       
	       //reading data from excel file  
	       FileInputStream fis1 = new FileInputStream("./testdata/TestData1.xlsx");
			Workbook wb = WorkbookFactory.create(fis1);
			Sheet sh = wb.getSheet("Sheet1");
			Row r = sh.getRow(9);
			String organizationName = r.getCell(1).getStringCellValue()+jlib.getRandomNumber();	
			String testcasename = r.getCell(1).toString()+jlib.getRandomNumber();
			wb.close();
			
			 
		       driver.manage().window().maximize();
				  driver.get(URL);
				  wlib.waitforpagetoload(driver);
				  driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				  driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				  driver.findElement(By.id("submitbutton")).click();
				  driver.findElement(By.linkText("Organizations")).click(); 				  
				  driver.findElement(By.xpath("//img[@title='Create Organization...\']")).click();
				  driver.findElement(By.name("accountname")).sendKeys(organizationName);				  
				  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				  String actorg = driver.findElement(By.id("mouseArea_Organization Name")).getText();
				  if(actorg.trim().equals(organizationName)) {
					  System.out.println(organizationName+" is verified");
				  }
				  else {
					  System.out.println(organizationName + "is not verified");
				  }
				  Thread.sleep(2000);
				  driver.findElement(By.linkText("Contacts")).click(); 				  
				  Thread.sleep(2000);
				  driver.findElement(By.xpath("//img[@title='Create Contact...\']")).click();
				  driver.findElement(By.name("lastname")).sendKeys(testcasename);	
				  driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();
				  
				  //String parentWindow=driver.getWindowHandle();
		          wlib.switchtonewtab(driver," http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=");
//				  Set<String> childWindows=driver.getWindowHandles();
//				
//				  for(String window: childWindows)
//				  {
//					  driver.switchTo().window(window);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
//					 
//					  if(driver.getCurrentUrl().equals("http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid="))
//					  {
					  Thread.sleep(2000);
					  driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(organizationName);
					 
					  driver.findElement(By.name("search")).click();
					  Thread.sleep(3000);
					 driver.findElement(By.xpath("//a[@id='1']")).click();
					 Thread.sleep(2000);
					 wlib.switchtonewtab(driver, "Contactss&action");
					 driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
//					  }
//				  }
				  
                  
//				  
//				  driver.findElement(By.linkText("Contacts")).click(); 
//				  driver.findElement(By.name("search_text")).sendKeys(organizationName);
//				  driver.findElement(By.name("search")).click();
//				  WebElement ele = driver.findElement(By.name("search_field"));
//				  Select obj = new Select(ele);
//				  obj.selectByVisibleText(organizationName);
//				  driver.findElement(By.xpath("//input[@name='submit']")).click();
//				  
//				  
//				  driver.findElement(By.xpath("//input[@name=\"account_name\"]/following-sibling::img")).click();
//				  //Switch to child window
//				  Set<String> set = driver.getWindowHandles();
//				  for(String Window:set) {
//					  driver.switchTo().window();
//					  String title = driver.getTitle();
//				  }
//				  Iterator<String> it = set.iterator();
//				 
//				  While(it.hasNext()){ 
//					  String  windowId = it.next();
//					  driver.switchTo().window(windowId);
//				      
//					  String actUrl = driver.getCurrentUrl();
//					  if(actUrl.contains("module=Accounts")){
//						  break;
//					  }
//				  }
				  
//				  driver.findElement(By.xpath("//a[text()='"+organizationName+"']")).
//				  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
//				  
				  String actconorg = driver.findElement(By.id("mouseArea_Last Name")).getText();
				  if(actconorg.trim().equals(testcasename)) {
					  System.out.println(testcasename+" is verified");
				  }
				  else {
					  System.out.println(testcasename + "is not verified");
				  }
				  
	}

	

}
