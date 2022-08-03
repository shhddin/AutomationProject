package com.libertymutual.Test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import base.BaseClass;

public class AutoTest extends BaseClass {
	
	@Test
	public void AutoTesting() {
		homePage.HomepageSteps("Only pay for what you need", "19149");
		
	}

}
