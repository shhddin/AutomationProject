package calculator;

import org.testng.annotations.Test;

import base.BaseClass;

public class AutoCalculator extends BaseClass {

	@Test(groups = "own")
	public void autoCalculatorOwn() {
		homePage.homepageCalculate();
		calculate.calculateSteps1("Car Insurance Coverage Calculator | Liberty Mutual",
				"Car insurance coverage calculator", "19111", true, false, false, true, false, false);
		calculate.calculateSteps2();
		calculate.calculateSteps3And4();
	}

	@Test(groups = "lease")
	public void autoCalculatorLease() {
		homePage.homepageCalculate();
		calculate.calculateSteps1("Car Insurance Coverage Calculator | Liberty Mutual",
				"Car insurance coverage calculator", "19111", false, true, false, true, false, false);
		calculate.calculateSteps2();
		calculate.calculateSteps3And4();
	}

	@Test(groups = "finance")
	public void autoCalculatorFinance() {
		homePage.homepageCalculate();
		calculate.calculateSteps1("Car Insurance Coverage Calculator | Liberty Mutual",
				"Car insurance coverage calculator", "19111", false, true, false, true, false, false);
		calculate.calculateSteps2();
		calculate.calculateSteps3And4();
	}

}