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

	@FindBy(xpath = "//input[@id='quote-zipCode-input']")
	WebElement zipCodElement;

	@FindBy(xpath = "//button[contains(text(),'Get my price')]")
	WebElement getMyPriceButtonElement;

	private void getTitle(String expected) {
		assertEquals(expected,  commons.getText(title1Element));
	}

	private void inputZipcode(String value) {
		commons.inputValues(zipCodElement, value);
	}
	
	private void clickGetMyPriceButton() {
		commons.click(getMyPriceButtonElement);
	}
	

	public void HomepageSteps(String expected, String value) {
		getTitle(expected);
		inputZipcode(value);
		clickGetMyPriceButton();

	}

}
