package com.nearshoretechnology.qa.auto.cd_poc.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nearshoretechnology.qa.auto.cd_poc.po.GoogleSearchPage;
import com.nearshoretechnology.qa.auto.cd_poc.po.GoogleSearchResultsPage;
import com.nearshoretechnology.qa.auto.core.test.TestBase;

public class GoogleSearchResultsPageTest extends TestBase {

	
	@BeforeMethod
	public void beforeMethod() {
		webDriver.get("http://www.google.com/");
	}

	@Test
	public void searchResultsPageTitleIncludesTerm() {
		String term;
		GoogleSearchPage searchPage;

		term = "hello world!";

		searchPage = new GoogleSearchPage(webDriver);
		searchPage.search(term);

		Assert.assertTrue(webDriver.getTitle().startsWith(term));
	}

	@Test
	public void searchResultsPageTitleEndsWithGoogleSearch() {
		GoogleSearchPage searchPage;

		searchPage = new GoogleSearchPage(webDriver);
		searchPage.search("yolo");

		Assert.assertTrue(webDriver.getTitle().endsWith("Google Search"));
	}

	@Test
	public void searchResultsPageHasNavigator() {
		GoogleSearchPage searchPage;
		GoogleSearchResultsPage resultsPage;

		searchPage = new GoogleSearchPage(webDriver);
		resultsPage = searchPage.search("yolo");

		Assert.assertTrue(resultsPage.navigator.isDisplayed());
	}

	// failing on purpose
	@Test
	public void searchResultsPageSearchBoxHasTermInQuotes() {
		// will fail, search box has term without quotes
		GoogleSearchPage searchPage;
		GoogleSearchResultsPage resultsPage;
		String term;

		term = "yolo";
		searchPage = new GoogleSearchPage(webDriver);
		resultsPage = searchPage.search(term);

		Assert.assertEquals(resultsPage.searchBox.getAttribute("value"), String.format("\"%s\"", term));
	}

	@Test
	public void searchResultsPageFooterHasFiveLinks() {
		// will fail, footer has only four elements.
		GoogleSearchPage searchPage;
		GoogleSearchResultsPage resultsPage;

		searchPage = new GoogleSearchPage(webDriver);
		resultsPage = searchPage.search("yolo");

		Assert.assertEquals(resultsPage.footerLinksContainer.getText(), "Help Send feedback Privacy Terms Company");
	}

}
