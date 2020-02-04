package myTestProject.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import myTestProject.config.BaseVariables;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page extends BaseVariables {

	public String getTitle() {
		return driver.getTitle();
	}
	
	/**
	 * Очистка инпута и заполнение
	 * @param path - локатор к инпуту
	 * @param value - значение
	 */
	protected void setInput(By path, String value) {
		// .clear() для хрома не работает, версия хрома 79
		//driver.findElement(path).clear();
		WebElement elInput = driver.findElement(path);
		elInput.sendKeys(Keys.CONTROL + "a");
		elInput.sendKeys(Keys.DELETE);
		elInput.sendKeys(value);
	}

	/**
	 * Выбор селекта по value
	 * @param path - локатор к селекту
	 * @param value - выбираемое значение
	 */
	protected void setSelectByValue(By path, String value) {
		Select select = new Select(driver.findElement(path));
		select.selectByValue(value);
	}
	
	public boolean isElementPresent(By by){ 
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		boolean result = driver.findElements(by).size() > 0;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return result;
	}
}
