package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	FileInputStream fs;
	XSSFWorkbook wb;
	XSSFSheet sh;
	XSSFRow row;
	XSSFCell cell;	
	
	public String[][] fetchData(String fileName,String sheetName) throws IOException {
		
		
		String[][] data;
		//filename ".//testData/MMT_Test_Data.xlsx"
		fs = new FileInputStream(fileName);
		wb = new XSSFWorkbook(fs);
		sh = wb.getSheet(sheetName);
		
		int rowCount = sh.getLastRowNum()+1;
		int colCount = sh.getRow(0).getLastCellNum();
		
		
		data = new String[rowCount-1][colCount];
		
		
		for(int i = 1; i<rowCount;i++) {
		
			row = sh.getRow(i);
			for(int j = 0;j < colCount ; j++) {
		
				cell = row.getCell(j);		
				if(cell.getCellType().equals(CellType.STRING)) {
					data[i-1][j] = cell.getStringCellValue();
		
				}else if(cell.getCellType().equals(CellType.NUMERIC)){
					String reformattedString = reformatter(cell.getNumericCellValue());
					//data[i-1][j] = cell.getNumericCellValue()+"";
					data[i-1][j] = reformattedString;
		
				}
			}
		}		
		return data;
	}
	
	public static String reformatter(double value) {
		String convertedString = value+"";
		int index = convertedString.indexOf(".");
		convertedString = convertedString.substring(0, index);
		return convertedString;		
	}
}
