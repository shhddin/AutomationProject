package object;

import static utils.DataMap.*;
import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.Commons;
import utils.AutoData;

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

	@FindBy(xpath = "//select[@id='quote-lob-select']")
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
	@FindBy(xpath = "//a[contains(text(),'Car insurance coverage')]")
	WebElement calculatorWebElement;

	private void getTitle(String expected) {
		assertEquals(commons.getText(title1Element), expected);
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
		assertEquals(commons.getText(deniedH1Element), expectedH1);
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

	public void autoQuoteSteps(String expected, String zipcodesString, String expectedH1) {
		getTitle(expected);
		selectAuto();
		inputZipcode(zipcodesString);
		clickGetMyPriceButton();
		getTextDenied(expectedH1);
	}

	public void autoQuoteSteps(AutoData autoData) {
		getTitle(autoData.getExpectedTitle());
		selectAuto();
		inputZipcode(autoData.getZipcodesString());
		clickGetMyPriceButton();
		getTextDenied(autoData.getExpectedH1());
	}

	public void autoQuoteSteps(Map<String, String> map) {
		getTitle(map.get(Title.getValue()));
		selectAuto();
		inputZipcode(map.get(ZipCode.getValue()));
		clickGetMyPriceButton();
		getTextDenied(map.get(TextDenied.name()));
	}

	public void homepageCalculate() {
		moveToCalc();
	}

}
