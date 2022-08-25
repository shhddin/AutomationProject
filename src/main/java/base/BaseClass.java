package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import commons.CommonWaits;
import commons.Commons;
import io.github.bonigarcia.wdm.WebDriverManager;
import object.Calculate;
import object.HomePage;
import utils.Configuration;

public class BaseClass {
	public Configuration configuration = new Configuration(null);

	WebDriver driver;
	WebDriverWait wait;

	protected Commons commons;
	CommonWaits waits;
	protected HomePage homePage;
	protected Calculate calculate;

	@Parameters("browser")

	@BeforeMethod
	public void setup(String browser) throws InterruptedException {
		driver = localDriver(browser);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		Thread.sleep(5000);
		driver.get(configuration.getConfiguration("url"));
		driver.manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(configuration.getConfiguration("pageLoadTime"))));
		driver.manage().timeouts().implicitlyWait(
				Duration.ofSeconds(Integer.parseInt(configuration.getConfiguration("implicitlyWaitTime"))));
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
		waits = new CommonWaits(wait);
		commons = new Commons(driver, waits);
		homePage = new HomePage(driver, commons);
		calculate = new Calculate(driver, commons);
	}

	protected WebDriver getDriver() {
		return driver;
	}

	@AfterMethod
	public void terminate() {
		// driver.quit();
	}

}
