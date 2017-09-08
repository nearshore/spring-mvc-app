package com.nearshoretechnology.qa.auto.cd_poc.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.nearshoretechnology.qa.auto.core.po.WebElementContainer;

public class GoogleSearchResultsPage extends WebElementContainer {

	@FindBy(id = "lst-ib")
	public WebElement searchBox;

	@FindBy(id = "resultStats")
	public WebElement resultsCount;

	@FindBy(id = "foot")
	public WebElement navigator;

	@FindBy(id = "fsl")
	public WebElement footerLinksContainer;

	public GoogleSearchResultsPage(WebDriver webDriver) {
		super(webDriver);
	}

}
