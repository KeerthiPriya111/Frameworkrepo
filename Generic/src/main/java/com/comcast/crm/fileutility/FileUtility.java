package com.comcast.crm.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
  public String getDatafromPropertiesFile(String key) throws IOException {
	  FileInputStream fis = new FileInputStream("./configAppData/CD.properties");
	  Properties pro = new Properties();
	  pro.load(fis);
	  String data = pro.getProperty(key);
	return data;
	  
  }
}
