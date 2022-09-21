package reporting;

import java.util.logging.Level;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

public class Loggers {
	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger
			.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);

	public static void getLog(String msg) {
		// Java Console Logger
		LOGGER.log(Level.INFO, msg);
		// TestNG Reporter Log
		Reporter.log(msg + "<br>");
		// ExtentReport
		ExtentTestManager.getTest().log(Status.INFO, msg);
	}

}
