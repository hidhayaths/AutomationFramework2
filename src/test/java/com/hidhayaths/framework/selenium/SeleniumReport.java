package com.hidhayaths.framework.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.hidhayaths.framework.FrameworkException;
import com.hidhayaths.framework.Report;
import com.hidhayaths.framework.ReportSettings;
import com.hidhayaths.framework.ReportTheme;

/**
 * Class which extends the {@link Report} class with a Selenium specific
 * override for taking screenshots
 * 
 * @author hidhayaths
 */
public class SeleniumReport extends Report {

	private AutomationDriver driver;
	private final SeleniumTestParameters testParameters;

	/**
	 * Constructor to initialize the Report object
	 * 
	 * @param reportSettings
	 *            The {@link ReportSettings} object
	 * @param reportTheme
	 *            The {@link ReportTheme} object
	 * @param testParameters
	 */
	public SeleniumReport(ReportSettings reportSettings, ReportTheme reportTheme,
			SeleniumTestParameters testParameters) {
		super(reportSettings, reportTheme, testParameters);
		this.testParameters = testParameters;
	}

	/**
	 * Function to set the {@link AutomationDriver} object
	 * 
	 * @param driver
	 *            The {@link AutomationDriver} object
	 */

	public void setDriver(AutomationDriver driver) {
		this.driver = driver;
	}

	@Override
	protected void takeScreenshot(String screenshotPath) {
		if (driver == null) {
			throw new FrameworkException("Report.driver is not initialized!");
		}
		File scrFile = null;
		switch (testParameters.getExecutionMode()) {
		case API:
			break;
		case LOCAL:
		case GRID:
		case MOBILE:
		case PERFECTO:
		case SAUCELABS:
		case TESTOBJECT:
		case FASTEST:
		case BROWSERSTACK:

			try {
				if ("RemoteWebDriver".equals(driver.getWebDriver().getClass().getSimpleName())) {
					Capabilities capabilities = ((RemoteWebDriver) driver.getWebDriver()).getCapabilities();
					if ("htmlunit".equals(capabilities.getBrowserName())) {
						return; // Screenshots not supported in headless mode
					}
					WebDriver augmentedDriver = new Augmenter().augment(driver.getWebDriver());
					scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
				} else {
					scrFile = ((TakesScreenshot) driver.getWebDriver()).getScreenshotAs(OutputType.FILE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new FrameworkException("Error while capturing the screenshot");
			}

			break;

		}

		if (!(scrFile == null)) {
			try {
				FileUtils.copyFile(scrFile, new File(screenshotPath), true);
			} catch (IOException e) {
				e.printStackTrace();
				throw new FrameworkException("Error while writing screenshot to file");
			}
		}

	}

}