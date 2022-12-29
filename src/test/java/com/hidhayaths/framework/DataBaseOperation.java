package com.hidhayaths.framework;

import java.util.Date;
import java.util.Properties;

import com.hidhayaths.framework.selenium.SeleniumTestParameters;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DataBaseOperation {

	private SeleniumTestParameters testParameters;
	private Properties properties;
	private TestCaseBean testCaseBean;
	private String buildName;
	private String engineName;
	private String projectName;
	private String runConfigName;
	private String approach;
	private String onError;
	private boolean saveDataToDB;

	private MongoClient mongoclient;
	private DB database;
	private DBCollection collection;
	private DBObject testCaseDetails;

	public DataBaseOperation() {
		this.properties = Settings.getInstance();
		this.saveDataToDB = Boolean.parseBoolean(properties.getProperty("SaveDataToDB"));
		if (saveDataToDB) {
			createCollections();
		}

	}

	public void initializeTestParameters(SeleniumTestParameters testParameters) {
		this.testParameters = testParameters;
	}

	@SuppressWarnings("deprecation")
	private void createCollections() {

		String dbHost = properties.getProperty("DBHost");
		String dbPort = properties.getProperty("DBPort");

		try {
			// Check if that Collection exists
			// CognitiveDatabase - DB
			// ExecutionDetails - Collections
			// createCollection();
			// Creating collection if not exist if exist it will get the
			// collection
			mongoclient = new MongoClient(dbHost, Integer.valueOf(dbPort));
			database = mongoclient.getDB("CRAFTCentral");
			collection = database.getCollection("CraftExecutionDetails");
			testCaseDetails = new BasicDBObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameworkException("Error while Creating Data base" + e.getMessage());
		} finally {

		}
	}

	public void updateMongoDB(String engineName, TestCaseBean testCaseBean, String executionTime,
			String testCaseStatus) {

		if (saveDataToDB) {
			this.engineName = engineName;
			this.testCaseBean = testCaseBean;

			updateTestCaseDetailsMongo(executionTime, testCaseStatus);
			updateTestStepDetailsMongo();
		}

	}

	private void updateTestCaseDetailsMongo(String executionTime, String testStatus) {
		try {
			buildName = properties.getProperty("BuildName");
			projectName = properties.getProperty("ProjectName");
			approach = properties.getProperty("Approach");
			onError = properties.getProperty("OnError");
			runConfigName = properties.getProperty("RunConfiguration");

			// two more column
			String runName = TimeStamp.getInstance();
			runName = runName.substring(runName.lastIndexOf('/') + 1);
			String[] a = runName.split("_");
			String currentDate = a[a.length - 3];
			String timeOfExecution = a[a.length - 2] + "_" + a[a.length - 1];
			Date d = new Date();

			// adding to DB Object

			// Constant Values
			testCaseDetails.put("BuildName", buildName);
			testCaseDetails.put("Project Name", projectName);
			testCaseDetails.put("Engine Name", engineName);
			testCaseDetails.put("Approach", approach);
			testCaseDetails.put("OnError", onError);

			// Changing values
			testCaseDetails.put("Runconfiguration Name", runConfigName);
			testCaseDetails.put("ExecutionDate", currentDate);
			testCaseDetails.put("ExecutionTime", timeOfExecution);
			testCaseDetails.put("RunName", runName);
			testCaseDetails.put("TestScenario", testParameters.getCurrentScenario());
			testCaseDetails.put("TestCase", testParameters.getCurrentTestcase());
			testCaseDetails.put("TestInstance", testParameters.getCurrentTestInstance());
			testCaseDetails.put("TestDescription", testParameters.getCurrentTestDescription());
			testCaseDetails.put("TestIterationMode", testParameters.getIterationMode().toString());
			testCaseDetails.put("date",d);

			switch (testParameters.getExecutionMode()) {
			case API:
				testCaseDetails.put("TestExecutionMode", testParameters.getExecutionMode().toString());
				testCaseDetails.put("TestBrowser", "NA");
				testCaseDetails.put("TestPlatform", "NA");
				testCaseDetails.put("TestBrowserVersion", "NA");
				testCaseDetails.put("TestToolName", "NA");
				testCaseDetails.put("TestMobilePlatform", "NA");
				testCaseDetails.put("TestMobileVersion", "NA");
				testCaseDetails.put("TestDeviceName", "NA");
				break;
			case LOCAL:
			case GRID:

				testCaseDetails.put("TestExecutionMode", testParameters.getExecutionMode().toString());
				testCaseDetails.put("TestBrowser", testParameters.getBrowser().toString());
				testCaseDetails.put("TestPlatform", testParameters.getPlatform().toString());
				if (testParameters.getBrowserVersion() == null) {
					testCaseDetails.put("TestBrowserVersion", "NA");
				} else {
					testCaseDetails.put("TestBrowserVersion", testParameters.getBrowserVersion());
				}
				testCaseDetails.put("TestToolName", "NA");
				testCaseDetails.put("TestMobilePlatform", "NA");
				testCaseDetails.put("TestMobileVersion", "NA");
				testCaseDetails.put("TestDeviceName", "NA");
				break;

			case MOBILE:
			case TESTOBJECT:
			case SAUCELABS:
			case PERFECTO:
			case BROWSERSTACK:
			case FASTEST:

				testCaseDetails.put("TestExecutionMode", testParameters.getExecutionMode().toString());
				testCaseDetails.put("TestBrowser", "NA");
				testCaseDetails.put("TestPlatform", "NA");
				testCaseDetails.put("TestBrowserVersion", "NA");
				testCaseDetails.put("TestToolName", testParameters.getMobileToolName().toString());
				testCaseDetails.put("TestMobilePlatform", testParameters.getMobileExecutionPlatform().toString());
				testCaseDetails.put("TestMobileVersion", testParameters.getMobileOSVersion());
				testCaseDetails.put("TestDeviceName", testParameters.getDeviceName());

				break;
			default:
				throw new FrameworkException("Unhandled Execution Mode!");
			}

			testCaseDetails.put("TotalPassedSteps", testCaseBean.getTotalPassedSteps());
			testCaseDetails.put("TotalFailedSteps", testCaseBean.getTotalFailedSteps());
			testCaseDetails.put("FailureReason", testCaseBean.getFailureReason());
			testCaseDetails.put("TotalExecutionTime", executionTime);
			testCaseDetails.put("TestStatus", testStatus);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateTestStepDetailsMongo() {
		try {

			BasicDBList db1 = new BasicDBList();

			for (TestStepBean tsb : testCaseBean.getTestSteps()) {
				DBObject testStep = new BasicDBObject();
				testStep.put("StepName", tsb.getTestStepName());
				testStep.put("StepNo", tsb.getTestStepNumber());
				testStep.put("StepDescription", tsb.getTestStepDescription());
				testStep.put("StepStatus", tsb.getTestStepStatus());
				testStep.put("ExecutionTime", tsb.getTestStepExectuionTime());
				testStep.put("Iteration", tsb.getIteration());
				testStep.put("SubIteration", tsb.getSubIteration());
				testStep.put("BusinessComponent", tsb.getBusinessComponent());
				// Failure Details
				testStep.put("FailureReason", tsb.getFailureReason());
				testStep.put("MethodName", tsb.getMethodName());
				testStep.put("ClassName", tsb.getClassName());
				testStep.put("StackTrace", tsb.getStackTrace());
				testStep.put("BriefErroMessage", tsb.getBriefErrorMesg());
				db1.add(testStep);
			}
			testCaseDetails.put("Steps", db1);
			collection.insert(testCaseDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
