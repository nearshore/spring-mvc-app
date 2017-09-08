package com.nearshoretechnology.qa.auto.api;

import org.testng.ITestResult;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIConst;
import testlink.api.java.client.TestLinkAPIException;

public class TestLinkClient {

	private String devKey;
	private String tlURL;
	private TestLinkAPIClient client;

	public TestLinkClient(String url, String devKey) {
		tlURL = url;
		this.devKey = devKey;
		initApiClient();
	}

	public TestLinkClient() {
		this("http://52.88.145.49:8080/testlink/lib/api/xmlrpc/v1/xmlrpc.php",
				"268683f01d6baab30f40b7abab9b0100");
	}

	private void initApiClient() {
		client = new TestLinkAPIClient(devKey, tlURL);
	}

	public void createBuild(String projectName, String planName, String buildName, String buildNotes) throws TestLinkAPIException {
		client.createBuild(projectName, planName, buildName, buildNotes);
	}

	public void reportTestCaseResult(String projectName, String testPlanName, String buildName,
			String testCaseName, String testResultStatus, String execNotes) throws TestLinkAPIException {
		client.reportTestCaseResult(projectName, testPlanName, testCaseName, buildName, execNotes, testResultStatus);
	}

	public void reportTestCaseResult(TestLinkTestData tltd, ITestResult testResult, String notes) throws TestLinkAPIException {
		reportTestCaseResult(tltd.projectName, tltd.testPlanName, tltd.buildName, tltd.testCaseName,
				testResult.getStatus() == ITestResult.SUCCESS ? TestLinkAPIConst.TEST_PASSED :
					TestLinkAPIConst.TEST_FAILED, notes);
	}

	public void reportTestCaseResult(TestLinkTestData tltd, ITestResult testResult) throws TestLinkAPIException {
		reportTestCaseResult(tltd, testResult, "");
	}

}
