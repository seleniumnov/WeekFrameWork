package testData;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilities.Constants;

public class ReadExcel {

	public static File src;
	public static FileInputStream fin;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static int rows = 0;
	public static int cols = 0;
	public static Map<String, String> data;
	public static String key;
	public static String value;

	public static void main(String[] args) {

		readData(Constants.excelPath, "login", "CRMLogin");
	}

	public static Map<String, String> readData(String path, String sheetName, String testCasename) {

		try {
			data = new HashMap<String, String>();
			src = new File(path);
			fin = new FileInputStream(src);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			rows = sh.getLastRowNum() - sh.getFirstRowNum();
			cols = sh.getRow(0).getLastCellNum();

			for (int i = 1; i < rows; i++) {
				
				
				String cTestName = sh.getRow(i).getCell(0).getStringCellValue();
				if (cTestName.equals(testCasename)) {
					for (int j = 1; j < cols; j++) {

						key = sh.getRow(0).getCell(j).getStringCellValue();
						value = sh.getRow(i).getCell(j).getStringCellValue();
						data.put(key, value);
					}			
				}	
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return data;

	}

}
