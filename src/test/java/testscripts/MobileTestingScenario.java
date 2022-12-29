package testscripts;

import org.testng.annotations.Test;

import com.hidhayaths.driver.DriverScript;
import com.hidhayaths.driver.TestConfigurations;
import com.hidhayaths.framework.selenium.SeleniumTestParameters;

public class MobileTestingScenario extends TestConfigurations {

	@Test(dataProvider = "MobileDevice", dataProviderClass = TestConfigurations.class)
	public void eriBankSendPayment(SeleniumTestParameters testParameters) {

		testParameters.setCurrentTestDescription("Test for Login to EriBank App and MakePayment");

		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();

		tearDownTestRunner(testParameters, driverScript);
	}

	@Test(dataProvider = "MobileDevice")
	public void testMunkSignIn(SeleniumTestParameters testParameters) {

		testParameters.setCurrentTestDescription("Test for Login for Test Munk");

		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();

		tearDownTestRunner(testParameters, driverScript);
	}

	@Test(dataProvider = "MobileDevice")
	public void tiesSelection(SeleniumTestParameters testParameters) {

		testParameters.setCurrentTestDescription("Selecting Ties");

		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();

		tearDownTestRunner(testParameters, driverScript);
	}

}
