package calculator;

import org.testng.annotations.Test;

import base.BaseClass;

public class AutoCalculator extends BaseClass {

	@Test(enabled = true)
	public void autoCalculator() {
		homePage.homepageCalculate();
		calculate.calculateSteps1("Car Insurance Coverage Calculator | Liberty Mutual",
				"Car insurance coverage calculator", "19111", true, false, false, true, false, false);
		calculate.calculateSteps2();
		calculate.calculateSteps3And4();
	}

}