package com.nearshoretechnology.qa.auto.core.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WebElementContainer {

	protected WebDriver webDriver;

	public WebElementContainer(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(this.webDriver, this);
	}

}
