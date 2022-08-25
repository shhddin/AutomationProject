package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import reporting.Loggers;

public class ExcelUtil {

	Workbook workbook;
	Sheet sheet;

	FileInputStream fileInputStream;

	public ExcelUtil(String path, String sheetName) {
		try {
			fileInputStream = new FileInputStream(path);

		} catch (IOException e) {
			e.printStackTrace();
			Loggers.getLog("File not Found---->" + path);
		}
		if (path.endsWith(".xlsx")) {
			try {
				workbook = new XSSFWorkbook(fileInputStream);
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				Loggers.getLog("file not found---->" + path);

			}

		} else if (path.endsWith(".xls")) {
			try {
				workbook = new HSSFWorkbook(fileInputStream);
				fileInputStream.close();

			} catch (IOException e) {
				e.printStackTrace();
				Loggers.getLog("file not found---->" + path);
			}

		}
		sheet = workbook.getSheet(sheetName);
	}

	public String[][] dataObjects() {
		String[][] data = null;
		int yRowsNumber = 0;
		int totalCellNumber = sheet.getRow(0).getLastCellNum() - 2;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			try {
				if (sheet.getRow(i).getCell(1).getStringCellValue().equalsIgnoreCase("Y")) {
					yRowsNumber++;
				}

			} catch (NullPointerException e) {
			}
		}

		data = new String[yRowsNumber][totalCellNumber];

		int row = 0;
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			try {
				if (sheet.getRow(i).getCell(1).getStringCellValue().equalsIgnoreCase("Y")) {
					for (int j = 2; j < sheet.getRow(i).getLastCellNum(); j++) {
						String value = sheet.getRow(i).getCell(j).getStringCellValue();
						// System.out.println(value);
						data[row][j - 2] = value;
					}
					row++;
				}
			} catch (NullPointerException e) {
				//
			}
		}
		return data;
	}

}
