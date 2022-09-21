package homePage;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import base.BaseClass;
import utils.AutoData;
import utils.ExcelUtil;

public class AutoTestExcel2D extends BaseClass {

	@DataProvider(name = "autoExcel")
	public Object[][] autoData() {
		String pathString = configuration.getConfiguration("excelPath");
		String sheetNameString = configuration.getConfiguration("excelSheet");
		ExcelUtil excelUtil = new ExcelUtil(pathString, sheetNameString);
		return excelUtil.dataObjects();
	}

	@Test(enabled = true, dataProvider = "autoExcel")
	public void getQuote(AutoData autoData) {
		homePage.autoQuoteSteps(autoData);

	}
}
