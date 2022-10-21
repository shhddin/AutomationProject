package base;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import reporting.ExtentTestManager;

import commons.CommonWaits;
import commons.Commons;
import io.github.bonigarcia.wdm.WebDriverManager;
import object.Calculate;
import object.HomePage;
import reporting.ExtentManager;
import utils.Configuration;

public class BaseClass {

    public Configuration configuration = Configuration.getInstance("configuration/configure.properties");

    WebDriver driver;
    WebDriverWait wait;
    CommonWaits waits;
    ExtentReports extent;

    protected Commons commons;
    protected HomePage homePage;
    protected Calculate calculate;

    @BeforeSuite
    public void initiatingReport() {
	extent = ExtentManager.initiatingReport();
    }

    @BeforeMethod
    public void beforeEachTest(Method method) {
	String className = method.getDeclaringClass().getSimpleName();
	ExtentTestManager.creatingTest(method.getName());
	ExtentTestManager.getTest().assignCategory(className);
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) throws InterruptedException {
	if (browser.equalsIgnoreCase("browserStackCloud")) {
	    driver = browserStackDriver();
	} else {
	    driver = localDriver(browser);
	}
	configuration = Configuration.getInstance("configuration/configure.properties");
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	Thread.sleep(5000);
	driver.get(configuration.getConfiguration("url"));
	driver.manage().timeouts()
		.pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(configuration.getConfiguration("pageLoadTime"))));
	driver.manage().timeouts().implicitlyWait(
		Duration.ofSeconds(Integer.parseInt(configuration.getConfiguration("implicitlyWaitTime"))));
	wait = new WebDriverWait(driver,
		Duration.ofSeconds(Integer.parseInt(configuration.getConfiguration("explicitWait"))));
	initClasses();
    }

    @AfterMethod
    public void afterEachTest(Method method, ITestResult result) {
	for (String group : result.getMethod().getGroups()) {
	    ExtentTestManager.getTest().assignCategory(group);
	}
	if (result.getStatus() == ITestResult.SUCCESS) {
	    ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
	} else if (result.getStatus() == ITestResult.SKIP) {
	    ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	} else {
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed \n" + result.getThrowable());
	    ExtentTestManager.getTest().addScreenCaptureFromPath(commons.getScreenshot(method.getName()));
	}
    }

    private WebDriver localDriver(String browserName) {
	if (browserName.equalsIgnoreCase("chrome")) {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	} else if (browserName.equalsIgnoreCase("firefox")) {
	    WebDriverManager.firefoxdriver().setup();
	    driver = new FirefoxDriver();
	} else if (browserName.equalsIgnoreCase("safari")) {
	    WebDriverManager.safaridriver().setup();
	    driver = new SafariDriver();
	} else if (browserName.equalsIgnoreCase("edge")) {
	    WebDriverManager.edgedriver().setup();
	    driver = new EdgeDriver();
	} else {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	}
	return driver;
    }

    public WebDriver browserStackDriver() {
	configuration = Configuration.getInstance("configuration/bsConfig.properties");
	MutableCapabilities capabilities = new MutableCapabilities();
	capabilities.setCapability("browserName", configuration.getConfiguration("browser"));
	capabilities.setCapability("browserVersion", configuration.getConfiguration("browserVersion"));
	capabilities.setCapability("browserstack.user", configuration.getConfiguration("user"));
	capabilities.setCapability("browserstack.key", configuration.getConfiguration("pass"));

	HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
	browserstackOptions.put("os", configuration.getConfiguration("os"));
	browserstackOptions.put("osVersion", configuration.getConfiguration("osVersion"));
	capabilities.setCapability("bstack:options", browserstackOptions);
	try {
	    driver = new RemoteWebDriver(new URL(configuration.getConfiguration("url")), capabilities);
	} catch (MalformedURLException e) {
	    e.printStackTrace();
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
	driver.quit();
    }

    @AfterSuite
    public void endReport() {
	extent.flush();
    }

}
