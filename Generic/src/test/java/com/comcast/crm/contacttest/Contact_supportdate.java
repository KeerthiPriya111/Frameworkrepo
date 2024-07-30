package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.webdriverutility.JavaUtility;

public class Contact_supportdate {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		JavaUtility jlib = new JavaUtility();
		  FileInputStream fis = new FileInputStream("./configAppData/CD.properties");
	       Properties pro = new Properties();
	       pro.load(fis);
	       
	       String Browser = pro.getProperty("browser");
	       String URL = pro.getProperty("url");
	       String USERNAME = pro.getProperty("username");
	       String PASSWORD = pro.getProperty("password");
	       
	      
	       
	       //reading data from excel file  
	       FileInputStream fis1 = new FileInputStream("./testdata/TestData.xlsx");
			Workbook wb = WorkbookFactory.create(fis1);
			Sheet sh = wb.getSheet("work");
			Row r = sh.getRow(1);
			String organizationName = r.getCell(2).getStringCellValue()+jlib.getRandomNumber();		
			wb.close();
			
			 WebDriver driver = null;
			 if(Browser.equals("Firefox")) {
				 driver =  new FirefoxDriver();
			 }
			 else {
				 driver = new ChromeDriver();
			 }
		      // driver = new ChromeDriver();
		       driver.manage().window().maximize();
				  driver.get(URL);
				  
				  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				  driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				  driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				  driver.findElement(By.id("submitbutton")).click();
				  driver.findElement(By.linkText("Contacts")).click(); 
				  
				  driver.findElement(By.xpath("//img[@title='Create Contact...\']")).click();
				  //
				  String startdate = jlib.getSystemDateYYYYDDMM();
				  String endDate = jlib.getRequiredDateYYYYMMDD(30);
				  
			    
			      
				  driver.findElement(By.name("lastname")).sendKeys(organizationName);
				  driver.findElement(By.name("support_start_date")).clear();
				  driver.findElement(By.name("support_start_date")).sendKeys(startdate);
				  
				  driver.findElement(By.name("support_end_date")).clear();
				  driver.findElement(By.name("support_end_date")).sendKeys(endDate);
				  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();				 
				  String actstart = driver.findElement(By.id("dtlview_Support Start Date")).getText();
				  if(actstart.equals(startdate)) {
					  System.out.println(startdate+" is verified");
				  }
				  else {
					  System.out.println(startdate + "is not verified");
				  }
				  String actend = driver.findElement(By.id("dtlview_Support End Date")).getText();
				  if(actend.equals(endDate)) {
					  System.out.println(endDate+" is verified");
				  }
				  else {
					  System.out.println(endDate + "is not verified");
				  }
	}

}
