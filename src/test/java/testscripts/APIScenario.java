package testscripts;

import org.testng.annotations.Test;

import com.hidhayaths.driver.DriverScript;
import com.hidhayaths.driver.TestConfigurations;
import com.hidhayaths.framework.selenium.SeleniumTestParameters;

public class APIScenario extends TestConfigurations {

	@Test(dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void validateUserData_REST(SeleniumTestParameters testParameters) {

		testParameters.setCurrentTestDescription("Test API for User data");

		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();

		tearDownTestRunner(testParameters, driverScript);
	}

	@Test(dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void temparatureConvertion_SOAP(SeleniumTestParameters testParameters) {

		testParameters.setCurrentTestDescription("Test API for Temparture Convertions");
		
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();

		tearDownTestRunner(testParameters, driverScript);
	}

}
