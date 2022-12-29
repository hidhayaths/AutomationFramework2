package com.hidhayaths.driver;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.hidhayaths.framework.APIReusuableLibrary;
import com.hidhayaths.framework.DataTable;
import com.hidhayaths.framework.selenium.AutomationDriver;
import com.hidhayaths.framework.selenium.SeleniumReport;
import com.hidhayaths.framework.selenium.WebDriverUtil;

/**
 * Wrapper class for common framework objects, to be used across the entire test
 * case and dependent libraries
 * 
 * @author hidhayaths
 */
public class ScriptHelper {

	private final DataTable dataTable;
	private final SeleniumReport report;
	private AutomationDriver automationDriver;
	private WebDriverUtil driverUtil;
	private APIReusuableLibrary apiDriver;
	private ExtentTest extentTest;
	private Map<String, String> reusableHandle;

	/**
	 * Constructor to initialize all the objects wrapped by the
	 * {@link ScriptHelper} class
	 * 
	 * @param dataTable
	 *            The {@link DataTable} object
	 * @param report
	 *            The {@link SeleniumReport} object
	 * @param driver
	 *            The {@link WebDriver} object
	 * @param driverUtil
	 *            The {@link WebDriverUtil} object
	 * @param reusableHandle
	 * @param extentTest
	 * @param apiDriver
	 */

	public ScriptHelper(DataTable dataTable, SeleniumReport report, AutomationDriver automationDriver,
						WebDriverUtil driverUtil, APIReusuableLibrary apiDriver, ExtentTest extentTest,
						Map<String, String> reusableHandle) {
		this.dataTable = dataTable;
		this.report = report;
		this.automationDriver = automationDriver;
		this.driverUtil = driverUtil;
		this.apiDriver = apiDriver;
		this.extentTest = extentTest;
		this.reusableHandle = reusableHandle;
	}

	/**
	 * Function to get the {@link DataTable} object
	 * 
	 * @return The {@link DataTable} object
	 */
	public DataTable getDataTable() {
		return dataTable;
	}

	/**
	 * Function to get the {@link SeleniumReport} object
	 * 
	 * @return The {@link SeleniumReport} object
	 */
	public SeleniumReport getReport() {
		return report;
	}

	/**
	 * Function to get the {@link AutomationDriver} object
	 * 
	 * @return The {@link AutomationDriver} object
	 */
	public AutomationDriver getcraftDriver() {
		return automationDriver;
	}

	/**
	 * Function to get the {@link WebDriverUtil} object
	 * 
	 * @return The {@link WebDriverUtil} object
	 */
	public WebDriverUtil getDriverUtil() {
		return driverUtil;
	}

	/**
	 * Function to get the {@link APIReusuableLibrary} object
	 * 
	 * @return The {@link APIReusuableLibrary} object
	 */
	public APIReusuableLibrary getApiDriver() {
		return apiDriver;
	}

	/**
	 * Function to get the {@link ExtentTest} object
	 * 
	 * @return The {@link ExtentTest} object
	 */
	public ExtentTest getExtentTest() {
		return extentTest;
	}

	public Map<String, String> getReusablehandle() {
		return reusableHandle;
	}

}