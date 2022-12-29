package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.hidhayaths.driver.DriverScript;
import com.hidhayaths.driver.ReusableLibrary;
import com.hidhayaths.driver.ScriptHelper;
import com.hidhayaths.framework.Status;

/**
 * MasterPage Abstract class
 * 
 * @author hidhayaths
 */
abstract class MasterPage extends ReusableLibrary {
	// UI Map object definitions
	// Links
	protected final By lnkSignOff = By.linkText("SIGN-OFF");
	protected final By lnkRegister = By.linkText("REGISTER");

	/**
	 * Constructor to initialize the functional library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	protected MasterPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		PageFactory.initElements(driver.getWebDriver(), this);
	}

	public UserRegistrationPage clickRegister() {
		report.updateTestLog("Click Register", "Click on the REGISTER link", Status.DONE);
		driver.findElement(lnkRegister).click();
		return new UserRegistrationPage(scriptHelper);
	}

	public SignOnPage logout() {
		report.updateTestLog("Logout", "Click the sign-off link", Status.DONE);
		driver.findElement(lnkSignOff).click();
		return new SignOnPage(scriptHelper);
	}
}