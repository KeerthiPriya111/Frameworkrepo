package com.comcast.crm.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
public String getDataFromExcel(String sheetname,int rowNum,int cellNum) throws EncryptedDocumentException, IOException {
	FileInputStream fis = new FileInputStream("./testdata/TestData1.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sh =wb.getSheet(sheetname);
	String val  =sh.getRow(rowNum).getCell(cellNum).toString();
	return val;
}
public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException {
	FileInputStream fis = new FileInputStream("./testdata/TestData1.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	int rownum = wb.getSheet(sheetname).getLastRowNum();	
	return rownum;
}
public void setDataIntoExcel(String sheetname, int rownum, int cellnum, String data) throws EncryptedDocumentException, IOException {
	FileInputStream fis = new FileInputStream("./testdata/TestData1.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	wb.getSheet(sheetname).getRow(rownum).createCell(cellnum);
	
	FileOutputStream fos = new FileOutputStream("./testdata/TestData1.xlsx");
	wb.write(fos);
	wb.close();
}
}
