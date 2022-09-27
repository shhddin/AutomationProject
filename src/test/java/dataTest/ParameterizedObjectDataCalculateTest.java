package dataTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import utils.CalculateData;

public class ParameterizedObjectDataCalculateTest extends BaseClass {

	@DataProvider(name = "calculateObject")
	public Object[][] calculateDataObject() {
		CalculateData calculateData1 = new CalculateData("19111", false, false, true, false, false, true);
		CalculateData calculateData2 = new CalculateData("19149", false, false, true, true, false, false);

		return new Object[][] { { calculateData1 }, { calculateData2 } };
	}

	@Test(dataProvider = "calculateObject")
	public void calculateStepsTest(CalculateData calculateData) {
		homePage.homepageCalculate();
		calculate.calculateSteps(calculateData);
	}

}