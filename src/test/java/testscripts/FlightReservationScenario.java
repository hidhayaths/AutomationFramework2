package testscripts;

import org.testng.annotations.Test;

import com.hidhayaths.driver.DriverScript;
import com.hidhayaths.driver.TestConfigurations;
import com.hidhayaths.framework.selenium.SeleniumTestParameters;

public class FlightReservationScenario extends TestConfigurations {

	@Test(dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void testForBookTicketsWithValidCreditCard(SeleniumTestParameters testParameters) {

		testParameters.setCurrentTestDescription("Test for book flight tickets and verify booking");

		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();

		tearDownTestRunner(testParameters, driverScript);
	}

}
