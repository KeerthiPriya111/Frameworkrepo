package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v117.browser.Browser;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.xml.XmlTest;

import com.comcast.crm.webdriverutility.JavaUtility;

public class Createorgtest {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
			 
		       FileInputStream fis = new FileInputStream("./configAppData/CD.properties");
		       Properties pro = new Properties();
		       pro.load(fis);
		       
		       String Browser = pro.getProperty("browser");
		       String URL = pro.getProperty("url");
		       String USERNAME = pro.getProperty("username");
		       String PASSWORD = pro.getProperty("password");
//		       //data from xml file
//		       String URL = test.getParameter("url");
//			   String Brow = test.getParameter("browser");
//			   String UN = test.getParameter("username");
//			   String psd = test.getParameter("password");
//		       //reading data from command line
//		       String URL = System.getProperty("url");
//			   String Brow = System.getProperty("browser");
//			   String UN = System.getProperty("username");
//			   String psd = System.getProperty("password");
		       //random number
//		       Random ran = new Random();
//		       int ranint = ran.nextInt(1000);
		       JavaUtility jlib = new JavaUtility();
		  
		       //reading data from excel file  
		       FileInputStream fis1 = new FileInputStream("./src/test/resources/TestData1.xlsx");
				Workbook wb = WorkbookFactory.create(fis1);
				Sheet sh = wb.getSheet("Sheet1");
				Row r = sh.getRow(1);
				String organizationName = r.getCell(2).toString();
				wb.close();
//		    		   
		       WebDriver driver = null;
		       
		       if(Browser.equals("chrome")) {
		    	   driver = new ChromeDriver();
		       }
		       else {
		      	 driver = new EdgeDriver();
		       }
				   //driver = new ChromeDriver();
		       driver.manage().window().maximize();
				  driver.get(URL);
				  
				  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				  driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				  driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				  driver.findElement(By.id("submitbutton")).click();
				  driver.findElement(By.linkText("Organizations")).click(); 
				  
				  driver.findElement(By.xpath("//img[@title='Create Organization...\']")).click();
				  driver.findElement(By.name("accountname")).sendKeys(organizationName+jlib.getRandomNumber());
				  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				  Thread.sleep(2000);
				  //verify headermsg expected result
				  String text = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				  if(text.contains(organizationName)) {
					  System.out.println(organizationName + "is cretaed");
				  }  
				  else {
					  System.out.println(organizationName + "is failed");
				  }
				  //verify Header orgname info  expected result
				  String actTc_name = driver.findElement(By.id("dtlview_Organization Name")).getText();
				  if(actTc_name.contains(organizationName)) {
					  System.out.println(organizationName + "is cretaed");
				  }  
				  else {
					  System.out.println(organizationName + "is failed");
				  }
				  //Verify Createorg with industry dropdown
				  
//				  Actions act = new Actions(driver);
//				   WebElement val  = driver.findElement(By.xpath("(//td[@valign='bottom'])[2]"));
//				   System.out.println("1");
//				  act.moveToElement(val).perform();
//				  System.out.println("2");
				  //WebElement signout = driver.findElement(By.linkText("Sign Out"));
				  //System.out.println("3");
				 // act.moveToElement(signout).click().perform();
				 // System.out.println("4");
				  //driver.quit();
		       
			}
	}


