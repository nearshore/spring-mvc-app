package com.nearshoretechnology.qa.auto.cd_poc.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.nearshoretechnology.qa.auto.core.po.WebElementContainer;

/**
 * Represents the Google search page.
 * @author Victor Castillo (victor.castillo@nearshoretechnology.com)
 */
public class GoogleSearchPage extends WebElementContainer {

	@FindBy(id = "hplogo")
	public WebElement logo;

	@FindBy(name = "q")
	public WebElement searchBox;

	@FindBy(name = "btnK")
	public WebElement searchButton;

	@FindBy(name = "btnI")
	public WebElement imFeelingLuckyButton;

	public GoogleSearchPage(WebDriver webDriver) {
		super(webDriver);
	}

	public GoogleSearchResultsPage search(String term) {
		searchBox.sendKeys(term);
		searchBox.submit();

		return new GoogleSearchResultsPage(webDriver);
	}

}
