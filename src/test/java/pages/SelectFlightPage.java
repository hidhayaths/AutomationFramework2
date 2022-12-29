package pages;

import org.openqa.selenium.By;

import com.hidhayaths.driver.DriverScript;
import com.hidhayaths.driver.ScriptHelper;
import com.hidhayaths.framework.Status;

/**
 * SelectFlightPage class
 * 
 * @author hidhayaths
 */
public class SelectFlightPage extends MasterPage {
	// UI Map object definitions

	// Buttons
	private final By btnContinue = By.name("reserveFlights");

	/**
	 * Constructor to initialize the page
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public SelectFlightPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		if (!driver.getTitle().contains("Select a Flight")) {
			report.updateTestLog("Verify page title", "Select a Flight page expected, but not displayed!",
					Status.WARNING);
		}
	}

	public BookFlightPage selectFlights() {
		report.updateTestLog("Select Flights", "Select the first available flights", Status.DONE);
		driver.findElement(btnContinue).click();
		return new BookFlightPage(scriptHelper);
	}
}