package object;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.Commons;

public class HomePage {
	WebDriver driver;
	Commons commons;

	public HomePage(WebDriver driver, Commons commons) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.commons = commons;
	}

	@FindBy(xpath = "//h1[contains(text(),'Only pay')]")
	WebElement title1Element;
	
	@FindBy(xpath ="//select[@id='quote-lob-select']")
	WebElement selectAutoElement;
	

	@FindBy(xpath = "//input[@id='quote-zipCode-input']")
	WebElement zipCodElement;

	@FindBy(xpath = "//button[contains(text(),'Get my price')]")
	WebElement getMyPriceButtonElement;
	
	@FindBy(xpath = "//h1[contains(text(),'Access Denied')]")
	WebElement deniedH1Element;
	
	@FindBy(xpath = "//button[normalize-space(text())='Shop insurance']")
	WebElement shopInsuranceBtnWebElement;
	@FindBy(xpath = "//button[normalize-space(text())='Claims']")
	WebElement claimsBtnWebElement;
	@FindBy(xpath = "//button[normalize-space(text())='Customer support']")
	WebElement customerSupportWebElement;
	@FindBy(xpath = "//*[@data-testid='site-show-search-button']")
	WebElement searchBtnWebElement;
	
	
	
	
	
	
	
	
	
	@FindBy (xpath = "//a[contains(text(),'Car insurance coverage')]")
	WebElement calculatorWebElement;

	
	
	private void getTitle(String expected) {
		assertEquals(expected,  commons.getText(title1Element));
	}
	
	private void selectAuto() {
		commons.selectDropDown(selectAutoElement, "auto");
		
	}

	private void inputZipcode(String zipcodesString) {
		commons.inputValues(zipCodElement, zipcodesString);
	}
	
	private void clickGetMyPriceButton() {
		commons.click(getMyPriceButtonElement);
	}
	
	private void getTextDenied(String expectedH1) {
		assertEquals(expectedH1, commons.getText(deniedH1Element));
	}
	
	private void isElementDisplayed() {
		commons.isDisplayed(shopInsuranceBtnWebElement);
		commons.isDisplayed(claimsBtnWebElement);
		commons.isDisplayed(customerSupportWebElement);
		commons.isDisplayed(searchBtnWebElement);
	}
	
	
	private void moveToCalc() {
		commons.moveToElementClick(driver, calculatorWebElement);
	}	
	
	
	
	
	
	public void homePageElement() {
		isElementDisplayed();
	}

	public void autoQuoteSteps(String expected, String zipcodesString,String expectedH1) {
		getTitle(expected);
		selectAuto();
		inputZipcode(zipcodesString);
		clickGetMyPriceButton();
		getTextDenied(expectedH1);		
	}
	
	public void homepageCalculate() {
		moveToCalc();
	}

}
