package com.hidhayaths.driver;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.hidhayaths.framework.selenium.Browser;
import com.hidhayaths.framework.selenium.ExecutionMode;
import com.hidhayaths.framework.selenium.MobileExecutionPlatform;
import com.hidhayaths.framework.selenium.SeleniumParametersBuilders;
import com.hidhayaths.framework.selenium.ToolName;

public class TestConfigurations extends TestCase {

	@DataProvider(name = "DesktopBrowsers")
	public Object[][] desktopBrowsers(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] {
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).extentReport(extentReport)
						.extentTest(extentTest).testInstance("Instance1").executionMode(ExecutionMode.LOCAL)
						.browser(Browser.CHROME).build() }};
	}

	@DataProvider(name = "MobileDevice")
	public Object[][] mobileDevice(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] { { new SeleniumParametersBuilders(currentScenario, currentTestcase)
				.testInstance("Instance1").extentReport(extentReport).extentTest(extentTest)
				.executionMode(ExecutionMode.MOBILE).mobileExecutionPlatform(MobileExecutionPlatform.WEB_ANDROID)
				.toolName(ToolName.APPIUM).deviceName("1215fc22b4101e04").build() } };
	}

	@DataProvider(name = "API")
	public Object[][] api(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] {
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).testInstance("Instance1")
						.extentReport(extentReport).extentTest(extentTest).executionMode(ExecutionMode.API).build() } };
	}

	@DataProvider(name = "DesktopBrowsersParallel", parallel = true)
	public Object[][] desktopBrowsersParallel(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] {
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).extentReport(extentReport)
						.extentTest(extentTest).testInstance("Instance1").executionMode(ExecutionMode.LOCAL)
						.browser(Browser.CHROME).build() },
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).extentReport(extentReport)
						.extentTest(extentTest).testInstance("Instance2").executionMode(ExecutionMode.LOCAL)
						.browser(Browser.CHROME).build() },
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).extentReport(extentReport)
						.extentTest(extentTest).testInstance("Instance3").executionMode(ExecutionMode.LOCAL)
						.browser(Browser.CHROME).build() } };
	}

}