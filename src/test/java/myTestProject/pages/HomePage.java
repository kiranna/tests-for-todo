package myTestProject.pages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import myTestProject.models.ToDoModel;

/**
 * Sample page
 */
public class HomePage extends Page {

	private By btnAdd = By.cssSelector("button.is-primary[type='button']");
	private By btnDeleteAll = By.cssSelector("button.is-warning");
	private By btnCancelDelete = By.xpath("//*[@class='modal-card-foot']//button[@class='button']");
	private By btnConfirmDelete = By.xpath("//*[@class='modal-card-foot']//button[@class='button is-danger']");
	private By popupWindow = By.cssSelector(".modal-card");

	// окно добавления записи
	private By fldTitle = By.cssSelector(".modal-card input");
	private By fldPriority = By.cssSelector(".modal-card select");
	private By btnCloseModal = By.xpath("//button[@class='button']");
	private By btnSave = By.xpath("//*[@class='modal-card-foot']//button[@class='button is-primary']");

	private String tdID = "//td[@data-label='ID']";
	private String tdTodo = "//td[@data-label='Todo']";
	private String tdPriority = "//td[@data-label='Priority']";
	private String tdBtnEdit = "//td[@data-label='Edit']//button";
	private String tdBtnDelete = "//td[@data-label='Delete']//button";

	private String btnSortById = "//th[.//*[contains(text(),'ID')]]";
	private String btnSortByTitle = "//th[.//*[contains(text(),'Todo')]]";
	private String pathIconSortc = "[.//span[contains(@class,'icon is-small') and not(@style='display: none;')]]";
	private String pathSortAsc = "[.//span[not(contains(@class,'is-desc'))]]";
	private String pathSortDesc = "[.//span[contains(@class,'is-desc')]]";

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
		driver.findElement(btnConfirmDelete).click();
	}

	public void clickDeleteAll() {
		driver.findElement(btnDeleteAll).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(popupWindow));
	}

	public void cancelDeleteAll() {
		driver.findElement(btnCancelDelete).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(popupWindow));
	}

	public void confirmDeleteAll() {
		driver.findElement(btnConfirmDelete).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(popupWindow));
	}

	public void seeRecord(ToDoModel toDo) {
		String path = getRowPath(toDo);
		assertTrue(isElementPresent(By.xpath(path)),
				"Не найдена запись, должна присутствовать. xpath = " + path);
	}

	public void dontSeeRecord(ToDoModel toDo) {
		String path = getRowPath(toDo);
		assertFalse(isElementPresent(By.xpath(path)), "Найдена запись, должна отсутствовать. xpath = " + path);
	}

	/**
	 * Берет ID записи из таблицы
	 * 
	 * @param toDo
	 * @return
	 */
	public String getRecordID(ToDoModel toDo) {
		String path = getRowPath(toDo) + tdID;
		return driver.findElement(By.xpath(path)).getText();
	}

	/**
	 * Берет ID всех строк из таблицы
	 * @return
	 */
	public List<Integer> getIDFromAllRecords() {
		List<WebElement> elementList = driver.findElements(By.xpath(tdID));
		if (elementList.isEmpty()) {
			fail("Не найдены ID в общей таблице!");
		}

		List<Integer> idList = new ArrayList<Integer>();
		for (WebElement element : elementList) {
			idList.add(Integer.parseInt(element.getText()));
		}
		return idList;
	}
	
	/**
	 * Берет заголовки всех строк из таблицы
	 * @return
	 */
	public List<String> getTitleFromAllRecords() {
		List<WebElement> elementList = driver.findElements(By.xpath(tdTodo));
		if (elementList.isEmpty()) {
			fail("Не найдены Todo в общей таблице!");
		}

		List<String> titleList = new ArrayList<String>();
		for (WebElement element : elementList) {
			titleList.add(element.getText());
		}
		return titleList;
	}

	/**
	 * Сортировка по ID по возрастанию
	 */
	public void sortIDByAsc() {
		sortByAsc(btnSortById);
	}
	
	/**
	 * Сортировка по ID по убыванию
	 */
	public void sortIDByDesc() {
		sortByDesc(btnSortById);
	}
	
	/**
	 * Сортировка по заголовку по возрастанию
	 */
	public void sortTitleByAsc() {
		sortByAsc(btnSortByTitle);
	}
	
	/**
	 * Сортировка по заголовку по убыванию
	 */
	public void sortTitleByDesc() {
		sortByDesc(btnSortByTitle);
	}
	
	/**
	 * Сортировка по возрастанию
	 */
	private void sortByAsc(String column) {
		if(!isElementPresent(By.xpath(column + pathIconSortc))) {
			driver.findElement(By.xpath(column)).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(column + pathIconSortc)));
		
		if (isElementPresent(By.xpath(column + pathSortDesc))) {
			driver.findElement(By.xpath(column)).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(column + pathSortAsc)));
	}
	
	/**
	 * Сортировка по убыванию
	 */
	private void sortByDesc(String column) {
		if(!isElementPresent(By.xpath(column + pathIconSortc))) {
			driver.findElement(By.xpath(column)).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(column + pathIconSortc)));
		
		if (isElementPresent(By.xpath(column + pathSortAsc))) {
			driver.findElement(By.xpath(column)).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(column + pathSortDesc)));
	}
	
	/**
	 * Заполнение полей
	 * 
	 * @param toDo
	 */
	private void fillFields(ToDoModel toDo) {
		setInput(fldTitle, toDo.getTitle());
		setSelectByValue(fldPriority, toDo.getPriority());
	}

	/**
	 * Возвращает xpath искомой строки
	 * @param toDo
	 * @return
	 */
	private String getRowPath(ToDoModel toDo) {
		String rowPath = "//tr[";

		List<String> list = new ArrayList<String>();
		
		String ID = toDo.getID();
		if (ID != null && !ID.isEmpty()) {
			list.add("." + tdID + "[normalize-space()='" + ID + "']");
		}

		String title = toDo.getTitle();
		if (title != null && !title.isEmpty()) {
			list.add("." + tdTodo + "[normalize-space()='" + title + "']");
		}

		String priority = toDo.getPriority();
		if (priority != null && !priority.isEmpty()) {
			list.add("." + tdPriority + "[normalize-space()='" + priority + "']");
		}

		if (list.isEmpty()) {
			fail("В модели ToDoModel нет данных для построения xpath для поиска");
		}
		
		String joined = String.join(" and ", list);
		rowPath += joined + "]";
		
		return rowPath;
	}






}
