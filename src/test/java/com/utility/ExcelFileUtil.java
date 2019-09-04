package com.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	public String xlpath = "./TestInput/orangeHRM.xlsx";
	public  XSSFWorkbook wb;
	XSSFCell cell;
	public FileInputStream fis;
	public FileOutputStream fs;
	public ExcelFileUtil() throws IOException
	{
		fis =  new FileInputStream(xlpath);
		wb=new XSSFWorkbook(fis);
	}
	
	public  int rowCount(String sheetName)
	{
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		return rowCount;
	}
	
	public  int colCount(String sheetName,int rowNum)
	{
		int colCount = wb.getSheet(sheetName).getRow(rowNum).getLastCellNum();
		return colCount;
		
	}
	
	public String getData(String sheetName,int rowNum,int colNum)
	{
		String data ="";
		int	d;
		cell=wb.getSheet(sheetName).getRow(rowNum).getCell(colNum);
		
		if(cell.getCellType()==cell.CELL_TYPE_STRING)
		{
		data = wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).getStringCellValue();

		}else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
		d=(int)wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).getNumericCellValue();
		data=String.valueOf(d);
		}
		return data;
	}

	public void setData(String sheetName,int rowNum,int colNum,String status) throws IOException
	{
		wb.getSheet(sheetName).getRow(rowNum).createCell(colNum).setCellValue(status);
		fs=new FileOutputStream(xlpath);
		wb.write(fs);
	}

}