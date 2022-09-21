package homePage;

import static utils.DataMap.*;
import java.util.Map;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ExcelUtil;

import base.BaseClass;

public class HomePageTestMap extends BaseClass {
	
	@DataProvider(name = "excelMap")
	public Object[][] mapData(){
		String pathString = configuration.getConfiguration("excelPath");
		String sheetNameString = configuration.getConfiguration("excelSheetMap");
		ExcelUtil excelUtil = new ExcelUtil(pathString, sheetNameString);
		int size = excelUtil.dataMap().size();
		Object[][] objects2d = new Object[size][1];
		/*
		 * objects2d is an two-dimensional array
		 * Object[][] objects2d = new Object[size][1]
		 * define size = basically that many number of rows in dataMap
		 * 1 = is basically each dataMap index has one map
		 */
		for(int i = 0; i < size; i++) {
			objects2d[i][0] = excelUtil.dataMap().get(i);
		}
		return objects2d;
	}


	@Test(enabled = true, dataProvider = "excelMap") 
	public void autoTestingWithMapDataProvider(Map<String, String> map) {
		homePage.autoQuoteSteps(map.get("Title"),map.get("Zip Code"),map.get("Denied"));
	}
	
	@Test(enabled = false, dataProvider = "excelMap")
	public void autoTestingWithMapDataProviderWithEnum(Map<String, String> map) {
		homePage.autoQuoteSteps(map.get(Title.name()),map.get(ZipCode.getValue()),map.get(TextDenied.name()));
		
	}
	
	@Test(enabled = false, dataProvider = "excelMap")
	public void autoDataWithMapDataProviderWithMap(Map<String, String> map) {
		homePage.autoQuoteSteps(map);
	}
	
	
	
}
