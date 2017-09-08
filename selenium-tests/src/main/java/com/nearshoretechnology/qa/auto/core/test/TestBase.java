package com.nearshoretechnology.qa.auto.core.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.nearshoretechnology.qa.auto.api.TestLinkClient;
import com.nearshoretechnology.qa.auto.api.TestLinkTestData;

import testlink.api.java.client.TestLinkAPIException;

public class TestBase {

	protected WebDriver webDriver;
	protected TestLinkClient tlClient;
	protected TestLinkTestData tlData;

	@BeforeSuite
	public void _beforeSuite() {
		tlClient = new TestLinkClient();
		tlData = new TestLinkTestData();
		tlData.projectName = "CD-POC";
		tlData.testPlanName = "CD-POC_TP";

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		tlData.buildName =  dtf.format(now)+" CIBuild";

		try {
		
			tlClient.createBuild(tlData.projectName, tlData.testPlanName, tlData.buildName,"Generated Automatically by CI Bot");
		} catch(TestLinkAPIException error) {
			error.printStackTrace();
		}
	}
	
	public void setTestCaseName(String tcName)
	{
		tlData.testCaseName = tcName;
	}
	


	@BeforeClass
	public void _beforeClass() {
		tlData.testCaseName = this.getClass().getSimpleName();
	}

	@BeforeMethod
	public void _beforeMethod() {
		webDriver = new ChromeDriver();
	}

	@AfterMethod
	public void _afterMethod(ITestResult testResult) throws TestLinkAPIException {
		tlClient.reportTestCaseResult(tlData, testResult);
		webDriver.quit();
	}

	//@AfterClass
	//public void _afterClass() { }

}
