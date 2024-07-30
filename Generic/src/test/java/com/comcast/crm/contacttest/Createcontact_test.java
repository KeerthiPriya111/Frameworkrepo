package com.comcast.crm.contacttest;

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
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.fileutility.ExcelUtility;
import com.comcast.crm.fileutility.ExcelUtility2;
import com.comcast.crm.fileutility.FileUtility;
import com.comcast.crm.webdriverutility.JavaUtility;

public class Createcontact_test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileUtility flib = new FileUtility();
		 ExcelUtility2 elib = new ExcelUtility2();
		 JavaUtility jlib = new JavaUtility();
	       
	       String Browser = flib.getDatafromPropertiesFile("browser");
	       String URL = flib.getDatafromPropertiesFile("url");
	       String USERNAME = flib.getDatafromPropertiesFile("username");
	       String PASSWORD =  flib.getDatafromPropertiesFile("password");
	       
	      
	       
	       //reading data from excel file  
	      String lastName = elib.getDataFromExcel("work",2 ,2 )+jlib.getRandomNumber();
			
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
				  driver.findElement(By.name("lastname")).sendKeys(lastName);
				  
				  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				  String head = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
				  if(head.equals(lastName)) {
					  System.out.println(lastName+" is verified");
				  }
				  else {
					  System.out.println(lastName + "is not verified");
				  }
	}

}
