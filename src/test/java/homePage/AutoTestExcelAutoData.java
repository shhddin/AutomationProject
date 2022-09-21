package homePage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import utils.AutoData;
import utils.ExcelUtil;

public class AutoTestExcelAutoData extends BaseClass{

	@DataProvider(name = "excelAutoData")
	public Iterator<AutoData> autoData(){
		String pathString = configuration.getConfiguration("excelPath");
		String sheetNameString = configuration.getConfiguration("excelSheet");
		ExcelUtil excelUtil = new ExcelUtil(pathString, sheetNameString);
		String[][] objects = excelUtil.dataObjects();
		List<AutoData> list = new ArrayList<AutoData>();
		for(int i = 0; i < objects.length; i++) {
			AutoData autoData = new AutoData(objects[i][0],objects[i][1], objects[i][2]);
			list.add(autoData);
		}
		return list.iterator();
	}
	
	@Test(enabled = true, dataProvider = "excelAutoData")
	public void getAutoQuote(AutoData autoData) {
		homePage.autoQuoteSteps(autoData);
	}
}
