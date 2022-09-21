package commons;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.google.common.io.Files;

import reporting.Loggers;

public class Commons {
	WebDriver driver;
	CommonWaits waits;

	public Commons(WebDriver driver, CommonWaits waits) {
		this.driver = driver;
		this.waits = waits;
	}

	public String getTitle(WebDriver driver) {
		Loggers.getLog("The Title of the page is : " + driver.getTitle());
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		Loggers.getLog("Current url is " + getCurrentUrl(driver));
		return driver.getCurrentUrl();
	}

	public void inputValues(WebElement element, String value) {
		try {
			element.sendKeys(value);
			Loggers.getLog(value + "---> This value has been passed into the --" + element);

		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + ": This element Not Found");
			Assert.fail();
		}
	}

	public void inputValuesEnter(WebElement element, String value) {
		try {
			element.sendKeys(value, Keys.ENTER);
			Loggers.getLog(value + "-----This value passed into the --" + element);

		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + "----------: This element Not Found---------");
			Assert.fail();
		}
	}

	public void click(WebElement element) {
		try {
			element.click();
			Loggers.getLog(element + "---> This element has been clicked");

		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + ": This element Not Found");
			Assert.fail();
		}
	}

	public void clear(WebElement element) {
		try {
			element.clear();
			Loggers.getLog(element + "---> This element has been clicked");
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + ": This element Not Found");
			Assert.fail();
		}
	}

	public String getText(WebElement element) {
		String valueString = null;
		try {
			valueString = element.getText();
			Loggers.getLog(element + " ---> This element has text : " + valueString);
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + " : This element Not Found");
			Assert.fail();
		}
		return valueString;
	}

	public void selectDropDown(WebElement element, String value) {
		try {
			Select select = new Select(element);
			select.selectByValue(value);
			Loggers.getLog(element + " is selected and the value is " + value);
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + " This element is not found");
			Assert.fail();
		}

	}

	public void moveToElementClick(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		try {
			actions.moveToElement(element).click().build().perform();
			Loggers.getLog(element + " ------Moved to the element and clicked is performed:--");
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + "------not clicked or moved-----");
			Assert.fail();
		}

	}

	public boolean isEnabled(WebElement element) {
		boolean btn = false;
		try {
			btn = element.isEnabled();
			Loggers.getLog(element + "------ is enabled------");
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + "------ is not enabled-----");
			Assert.fail();
		}
		return btn;

	}

	public boolean isPresent(By locator) {
		List<WebElement> elements = driver.findElements(locator);
		if (elements.size() != 0) {
			Loggers.getLog(elements + " --- > This element is present and has match of : " + elements.size());
			return true;
		} else {
			Loggers.getLog(elements + " --- > This element is NOT present and no match found : " + elements.size());
			return false;
		}
	}

	public boolean isDisplayed(WebElement element) {
		boolean isElementDisplayed = false;
		try {
			isElementDisplayed = element.isDisplayed();
			Loggers.getLog(element + " --- This element is displayed : " + isElementDisplayed);
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.getLog(element + " --------: This element Not Found--------");
			Assert.fail();
		}
		return isElementDisplayed;
	}

	public String getScreenshot(String testName) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MMddyyyy_hh.mm.ss");
		String extension = format.format(date);
		File file = new File("screenShots/" + testName + "_" + extension + ".png");
		TakesScreenshot ss = (TakesScreenshot) driver;
		File outPutFile = ss.getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(outPutFile, file.getAbsoluteFile());
			Loggers.getLog("Test has been failed \nScreenshot taken here ---> " + file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			Loggers.getLog("Error while taking screenshot");
		}
		return file.getAbsolutePath();
	}

	public void jse(WebElement element, WebDriver driver) {
		JavascriptExecutor jscExecutor = (JavascriptExecutor) driver;
		jscExecutor.executeScript("arguments[0].isEnabled()", element);
	}
	
}
