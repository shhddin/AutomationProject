package object;




import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.Commons;
import utils.CalculateData;

public class Calculate {
	WebDriver driver;
	Commons commons;

	public Calculate(WebDriver driver, Commons commons) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.commons = commons;
	}

	@FindBy(xpath = "//h1[contains(text(),'Car insurance coverage calculator')]")
	WebElement header1WebElement;
	@FindBy(xpath = "//input[@name='zipCode']")
	WebElement zipWebElement;
	@FindBy(xpath = "//*[contains(text(),'Own')]")
	WebElement ownWebElement;
	@FindBy(xpath = "//*[contains(text(),'Lease')]")
	WebElement leaseWebElement;
	@FindBy(xpath = "//*[contains(text(),'Finance')]")
	WebElement financeWebElement;
	@FindBy(xpath = "//*[contains(text(),'Less than 1 year old')]")
	WebElement lessOneYearWebElement;
	@FindBy(xpath = "//*[contains(text(),'2-5 years old')]")
	WebElement twoToFiveYearWebElement;
	@FindBy(xpath = "//*[contains(text(),'More than 5 years old')]")
	WebElement moreThanFiveWebElement;
	@FindBy(xpath = "//button[contains(text(),'Continue to customization')]")
	WebElement continueWebElement;
	@FindBy(xpath = "(//button[@aria-label='Learn more'])[1]")
	WebElement helpBtn1WebElement;
	@FindBy(xpath = "(//button[@aria-label='Learn more'])[2]")
	WebElement helpBtn2WebElement;
	@FindBy(xpath = "(//button[@aria-label='Learn more'])[3]")
	WebElement helpBtn3WebElement;
	@FindBy(xpath = "//*[@data-testid='calculator-back']")
	WebElement backBtnWebElement;
	@FindBy(xpath = "//*[contains(text(),'Roadside help when you need it')]")
	WebElement roadsideCheckWebElement;
	@FindBy(xpath = "//*[contains(text(),'A rental car if your car is being repaired')]")
	WebElement rentalCarCheckBtnWebElement;
	@FindBy(xpath = "//*[contains(text(),'Protection from drivers without insurance')]")
	WebElement withoutInsuCheckWebElement;
	@FindBy(xpath = "(//*[contains(text(),'Continue customization')])[1]")
	WebElement continueSubmitBtnWebElement;
	@FindBy(xpath = "(//button[starts-with(@class,'lmig-Button lmig-Button--medium') and @type='submit'])[3]")
	WebElement continueSubmitBtn2WebElement;
	@FindBy(xpath = "(//*[@class='lm-FieldOption-text'])[13]")
	WebElement lowerDeductWebElement;
	@FindBy(xpath = "(//*[@class='lm-FieldOption-text'])[14]")
	WebElement goodDriveWebElement;
	@FindBy(xpath = "//*[contains(text(),'Show me my results')]")
	WebElement myResultBtnWebElement;

	// Page 1
	private void getTitle(String expectedTtile) {
		assertEquals(commons.getTitle(driver),expectedTtile);
		//Car Insurance Coverage Calculator | Liberty Mutual
	}

	private  void getH1(String expectedHeader1) {
	assertEquals(commons.getText(header1WebElement),expectedHeader1 );
		//Car insurance coverage calculator
	}

	private void inputZip(String zip) {
		commons.inputValues(zipWebElement, zip);
	}

	private void isOwn(boolean isOwn, boolean isLease, boolean isFinance) {
		if (isOwn) {
			commons.click(ownWebElement);
		} else if (isLease) {
			commons.click(leaseWebElement);

		} else if (isFinance) {
			commons.click(financeWebElement);
		}
	}

	private void howOld(boolean lessOne, boolean twoToFive, boolean moreThanFive) {
		if (lessOne) {
			commons.click(lessOneYearWebElement);
		} else if (twoToFive) {
			commons.click(twoToFiveYearWebElement);
		} else if (moreThanFive) {
			commons.click(moreThanFiveWebElement);
		}
	}

	private void clickContinue() {
		commons.click(continueWebElement);
	}

	// Page 2
	private void isBtnEnabled() {
		commons.isEnabled(helpBtn1WebElement);
		commons.isEnabled(helpBtn2WebElement);
		commons.isEnabled(helpBtn3WebElement);
		commons.isEnabled(backBtnWebElement);
	}

	private void checkBtn() {
		commons.click(roadsideCheckWebElement);
		commons.click(rentalCarCheckBtnWebElement);
		commons.click(withoutInsuCheckWebElement);
	}

	private void clickContinueSubmitBtn() {
		commons.click(continueSubmitBtnWebElement);
	}

	// Page 3 and 4
	private void clickContinueSubmitBtn2() {
		commons.click(continueSubmitBtn2WebElement);
	}

	private void finalPage() {
		commons.click(lowerDeductWebElement);
		commons.click(goodDriveWebElement);
		commons.click(myResultBtnWebElement);
	}

	// Page1
	public void calculateSteps1(String expectedTitle, String expectedHeader1, String zip, boolean isOwn, boolean isLease, boolean isFinance,
			boolean lessOne, boolean twoToFive, boolean moreThanFive) {
		getTitle(expectedTitle);;
		getH1(expectedHeader1);
		inputZip(zip);
		isOwn(isOwn, isLease, isFinance);
		howOld(lessOne, twoToFive, moreThanFive);
		clickContinue();
	}

	// Page2
	public void calculateSteps2() {
		isBtnEnabled();
		checkBtn();
		clickContinueSubmitBtn();
	}

	// Page3 & 4
	public void calculateSteps3And4() {
		clickContinueSubmitBtn2();
		finalPage();
	}

	// For data driven Testing
	public void calculateSteps(CalculateData calculateData) {
		inputZip(calculateData.getZip());
		isOwn(calculateData.isOwn(), calculateData.isLease(), calculateData.isFinance());
		howOld(calculateData.isLessOne(), calculateData.isTwoToFive(), calculateData.isMoreThanFive());
		clickContinue();
		isBtnEnabled();
		checkBtn();
		clickContinueSubmitBtn();
	}

}
