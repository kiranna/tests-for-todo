package myTestProject.pages;

import myTestProject.Config.BaseVariables;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page extends BaseVariables {

	public String getTitle() {
		return driver.getTitle();
	}

}
