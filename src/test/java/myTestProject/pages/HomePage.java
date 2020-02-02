package myTestProject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import myTestProject.models.ToDoModel;

/**
 * Sample page
 */
public class HomePage extends Page {

	private By btnAdd = By.cssSelector("button.is-primary[type='button']");
	private By btnDeleteAll = By.cssSelector("button.is-warning");

	private By fldTitle = By.cssSelector(".modal-card input");
	private By fldPriority = By.cssSelector(".modal-card select");
	private By btnCloseModal = By.xpath("//button[@class='button']");
	private By btnSave = By.xpath("//*[@class='modal-card-foot']//button[@class='button is-primary']");

	private String tdID = "//td[@data-label='ID']";
	private String tdTodo = "//td[@data-label='Todo']";
	private String tdPriority = "//td[@data-label='Priority']";
	private String tdBtnEdit = "//td[@data-label='Edit']//button";
	private String tdBtnDelete = "//td[@data-label='Delete']//button";

	private By btnCancelDelete = By.xpath("//*[@class='modal-card-foot']//button[@class='button']");
	private By btnDelete = By.xpath("//*[@class='modal-card-foot']//button[@class='button is-danger']");

	public void goToPage() {
		driver.get(baseUrl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnAdd));
	}

	public void addRecord(ToDoModel addToDo) {
		driver.findElement(btnAdd).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnSave));

		fillFields(addToDo);

		driver.findElement(btnSave).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(btnSave));
	}

	public void editRecord(ToDoModel oldDataToDo, ToDoModel newDataToDo) {
		By rowPath = By.xpath(getRowPath(oldDataToDo) + tdBtnEdit);
		driver.findElement(rowPath).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(btnSave));

		fillFields(newDataToDo);

		driver.findElement(btnSave).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(btnSave));
	}

	public void deleteRecord(ToDoModel deleteToDo) {
		By rowPath = By.xpath(getRowPath(deleteToDo) + tdBtnDelete);
		driver.findElement(rowPath).click();
		driver.findElement(btnDelete).click();
	}

	public void seeRecord(ToDoModel toDo) {
		String path = getRowPath(toDo);
		Assert.assertTrue(isElementPresent(By.xpath(path)),
				"Не найдена запись, должна присутствовать. xpath = " + path);
	}

	public void dontSeeRecord(ToDoModel toDo) {
		String path = getRowPath(toDo);
		Assert.assertFalse(isElementPresent(By.xpath(path)), 
				"Найдена запись, должна отсутствовать. xpath = " + path);
	}
	
	/**
	 * Берет ID записи из таблицы
	 * @param toDo
	 * @return
	 */
	public String getRecordID(ToDoModel toDo) {
		String path = getRowPath(toDo) + tdID;
		return driver.findElement(By.xpath(path)).getText();
	}

	/**
	 * Заполнение полей
	 * @param toDo
	 */
	private void fillFields(ToDoModel toDo) {
		setInput(fldTitle, toDo.getTitle());
		setSelectByValue(fldPriority, toDo.getPriority());
	}


	/**
	 * TODO подумать
	 * Получить xpath записи в виде строки
	 * @param toDo
	 * @return
	 */
	private String getRowPath(ToDoModel toDo) {
		String rowPath = "//tr[";

		String ID = toDo.getID();
		if (ID != null && !ID.isEmpty()) {
			rowPath += "." + tdID + "[normalize-space()='" + ID + "'] and ";
		}

		String title = toDo.getTitle();
		if (title != null && !title.isEmpty()) {
			rowPath += "." + tdTodo + "[normalize-space()='" + title + "'] and ";
		}

		String priority = toDo.getPriority();
		if (priority != null && !priority.isEmpty()) {
			rowPath += "." + tdPriority + "[normalize-space()='" + priority + "'] and ";
		}

		rowPath += "1=1]";

		return rowPath;
	}

}
