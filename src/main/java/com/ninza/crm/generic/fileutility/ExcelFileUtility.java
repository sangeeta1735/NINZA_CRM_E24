package com.ninza.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility 
{ 
	public String getDataFromExcelSheet(String sheet,int row,int cell) throws EncryptedDocumentException, IOException 
	{ 
		FileInputStream fis = new FileInputStream("./src/test/resources/testdata.xlsx"); 
		Workbook wb = WorkbookFactory.create(fis); 
		Sheet sh = wb.getSheet(sheet); 
		Row rw = sh.getRow(row); 
		Cell cl = rw.getCell(cell); 
		String data = cl.getStringCellValue(); 
		return data; 
		
		
	} 
	
	public int getGetTheRowCount (String sheet) throws EncryptedDocumentException, IOException 
	{ 
		FileInputStream fis = new FileInputStream("./src/test/resources/testdata.xlsx"); 
		Workbook wb = WorkbookFactory.create(fis); 
		int lastrownum= wb.getSheet(sheet).getLastRowNum(); 
		return lastrownum; 
		
		
	} 
	
	public List<String> toReadMultipleSetOfData (int lastrwnum, String sheet) throws EncryptedDocumentException, IOException 
	{ 
        List<String> myList = new ArrayList<>(); 
        
		FileInputStream fis = new FileInputStream("./src/test/resources/testdata.xlsx"); 
		Workbook wb = WorkbookFactory.create(fis); 
		for (int i = 1; i<=lastrwnum ; i++) 
		{ 
			String data = wb.getSheet(sheet).getRow(i).getCell(1).getStringCellValue(); 
			myList.add(data); 
			
			
		}
		
		return myList; 
		
	}
	
	


}
