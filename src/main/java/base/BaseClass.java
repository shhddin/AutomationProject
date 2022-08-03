package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import commons.Commons;
import io.github.bonigarcia.wdm.WebDriverManager;
import object.HomePage;
import utils.Configuration;

public class BaseClass {
	public Configuration configuration = new Configuration(null);

	WebDriver driver;

	protected Commons commons;
	protected HomePage homePage;

	@BeforeMethod
	public void setup() {
		driver = localDriver("firefox");
		driver.manage().window().maximize();
		driver.get(configuration.getConfiguration("url"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(configuration.getConfiguration("pageLoadTime"))));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(configuration.getConfiguration("implicitlyWaitTime"))));
		initClasses();
	}

	private WebDriver localDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		return driver;
	}

	private void initClasses() {
		commons = new Commons();
		homePage = new HomePage(driver, commons);
	}

	protected WebDriver getDriver() {
		return driver;
	}

	@AfterMethod
	public void terminate() {
		driver.quit();
	}

}
