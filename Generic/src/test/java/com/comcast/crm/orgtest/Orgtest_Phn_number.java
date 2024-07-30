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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.webdriverutility.JavaUtility;

public class Orgtest_Phn_number {

	public static void main(String[] args) throws IOException {
		
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
			Row r = sh.getRow(4);
			String organizationName = r.getCell(1).toString()+jlib.getRandomNumber();
			String phonenumber = r.getCell(3).getStringCellValue();
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
				  driver.findElement(By.linkText("Organizations")).click(); 
				  
				  driver.findElement(By.xpath("//img[@title='Create Organization...\']")).click();
				  driver.findElement(By.name("accountname")).sendKeys(organizationName);
				  driver.findElement(By.id("phone")).sendKeys(phonenumber);
				  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				  String num = driver.findElement(By.xpath("//span[@id='dtlview_Phone']")).getText();
				  if(num.equals(phonenumber)) {
					  System.out.println(phonenumber + "is verified");
				  }
				  else {
					  System.out.println(phonenumber + "is not verified");
				  }
				  driver.quit();
	}

}
