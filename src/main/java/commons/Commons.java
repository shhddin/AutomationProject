package commons;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import reporting.Loggers;

public class Commons {

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

}
