package homePage;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import base.BaseClass;

public class HomePageTest extends BaseClass {

	@Parameters("Zip")
	@Test(enabled = true, priority = 1)
	public void autoTestingS(String Zip) {
		homePage.homePageElement();
		homePage.autoQuoteSteps("Only pay for what you need", Zip, "Access Denied" );
		

	}
	
}
