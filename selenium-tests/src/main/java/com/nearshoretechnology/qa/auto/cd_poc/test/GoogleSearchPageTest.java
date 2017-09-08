package com.nearshoretechnology.qa.auto.cd_poc.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nearshoretechnology.qa.auto.cd_poc.po.GoogleSearchPage;
import com.nearshoretechnology.qa.auto.core.test.TestBase;

public class GoogleSearchPageTest extends TestBase {

	@BeforeMethod
	public void beforeMethod() {
		webDriver.get("http://www.google.com/");
	}

	@Test
	public void searchPageHasLogo() {
		GoogleSearchPage searchPage;
         this.setTestCaseName("SearchPageHasLogo");
		searchPage = new GoogleSearchPage(webDriver);

		Assert.assertTrue(searchPage.logo.isDisplayed());
	}

	@Test
	public void searchPageHasSearchBox() {
		GoogleSearchPage searchPage;
		this.setTestCaseName("searchPageHasSearchBox");

		searchPage = new GoogleSearchPage(webDriver);

		Assert.assertTrue(searchPage.searchBox.isDisplayed());
	}

	@Test
	public void searchPageHasSearchButton() {
		GoogleSearchPage searchPage;
		this.setTestCaseName("searchPageHasSearchButton");
		searchPage = new GoogleSearchPage(webDriver);

		Assert.assertTrue(searchPage.searchButton.isDisplayed());
	}

	@Test
	public void searchPageHasImFeelingLuckyButton() {
		GoogleSearchPage searchPage;
		this.setTestCaseName("searchPageHasImFeelingLuckyButton");
		searchPage = new GoogleSearchPage(webDriver);

		Assert.assertTrue(searchPage.imFeelingLuckyButton.isDisplayed());
	}

}
