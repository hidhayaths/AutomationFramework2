package com.hidhayaths.framework;

import java.util.List;

public class TestCaseBean {

	private int totalPassedSteps;
	private int totalFailedSteps;
	private String failureReason;
	private List<TestStepBean> testSteps;

	/**
	 * @return the totalPassedSteps
	 */
	public int getTotalPassedSteps() {
		return totalPassedSteps;
	}

	/**
	 * @param totalPassedSteps
	 *            the totalPassedSteps to set
	 */
	public void setTotalPassedSteps(int totalPassedSteps) {
		this.totalPassedSteps = totalPassedSteps;
	}

	/**
	 * @return the totalFailedSteps
	 */
	public int getTotalFailedSteps() {
		return totalFailedSteps;
	}

	/**
	 * @param totalFailedSteps
	 *            the totalFailedSteps to set
	 */
	public void setTotalFailedSteps(int totalFailedSteps) {
		this.totalFailedSteps = totalFailedSteps;
	}

	/**
	 * @return the failureReason
	 */
	public String getFailureReason() {
		return failureReason;
	}

	/**
	 * @param failureReason
	 *            the failureReason to set
	 */
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	/**
	 * @return the testSteps
	 */
	public List<TestStepBean> getTestSteps() {
		return testSteps;
	}

	/**
	 * @param testSteps
	 *            the testSteps to set
	 */
	public void setTestSteps(List<TestStepBean> testSteps) {
		this.testSteps = testSteps;
	}

}
