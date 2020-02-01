package myTestProject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Sample page
 */
public class HomePage extends Page {

	private By btnAdd = By.cssSelector("button.is-primary[type='button']");
	private By btnDeleteAll = By.cssSelector("button.is-warning");
	
	public void goToPage() {
		driver.get(baseUrl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnAdd));
	}
}
