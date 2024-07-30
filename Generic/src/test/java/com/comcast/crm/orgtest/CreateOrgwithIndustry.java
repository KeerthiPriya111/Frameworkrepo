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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.webdriverutility.JavaUtility;
import com.mysql.jdbc.Driver;

public class CreateOrgwithIndustry {

	public static void main(String[] args) throws IOException, InterruptedException {
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
	       FileInputStream fis1 = new FileInputStream("./testdata/TestData1.xlsx");
			Workbook wb = WorkbookFactory.create(fis1);
			Sheet sh = wb.getSheet("Sheet1");
			Row r = sh.getRow(5);
			String organizationName = r.getCell(2).toString()+jlib.getRandomNumber();
			String indus = r.getCell(3).toString();
			String  type= r.getCell(4).toString();
			wb.close();
			
			WebDriver driver = null;
			
			   if(Browser.equals("chrome")) {
		    	   driver = new ChromeDriver();
		       }
		       else {
		      	 driver = new EdgeDriver();
		       }
			
			  driver.manage().window().maximize();
			  driver.get(URL);
			  
			  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			  driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			  driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			  driver.findElement(By.id("submitbutton")).click();
			  driver.findElement(By.linkText("Organizations")).click(); 
			  
			  driver.findElement(By.xpath("//img[@title='Create Organization...\']")).click();
			  driver.findElement(By.name("accountname")).sendKeys(organizationName);
			  
			  WebElement ind = driver.findElement(By.name("industry"));
			  Select obj = new Select(ind);
			  obj.selectByVisibleText(indus);
			  
			  WebElement val = driver.findElement(By.name("accounttype"));
			  Select string = new Select(val);
			  string.selectByVisibleText(type);
			  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			  
			  
			  
			  //industry dropdown 
			 String dropdown =   driver.findElement(By.id("dtlview_Industry")).getText();
			 if(dropdown.equals(indus)) {
				 System.out.println(indus + "is verified == pass");
			 }
			 else {
				 System.out.println(indus + "is not verified" );
			 }
			 //type dropown
			 String typedrop = driver.findElement(By.id("dtlview_Type")).getText() ;
			 System.out.println(typedrop);
			 if(typedrop.equals(type)) {
				 System.out.println(type + "is  verified" );
			 }
			 else {
				 System.out.println(type + "is not verified" );
			 }			  
			 
			  
			  
			  
//	    		   
	       
	}

}
