package myTestProject;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import myTestProject.config.TestNgTestBase;
import myTestProject.models.ToDoModel;
import myTestProject.pages.HomePage;

public class BaseTest extends TestNgTestBase {

	private HomePage homepage;
	private ToDoModel addToDo;
	private ToDoModel editToDo;

	@BeforeSuite
	public void initPageObjects() {
		homepage = new HomePage();
		
		addToDo = new ToDoModel("fff" , "secondary");
		editToDo = new ToDoModel("aaa" , "important");
	}

	@Test(description = "Тест на открытие страницы")
	public void testCheckPage() {
		homepage.goToPage();
	}
	
	@Test(description = "Тест на добавление записи", dependsOnMethods = {"testCheckPage"})
	public void testAddRecord() {
		homepage.goToPage();
		homepage.addRecord(addToDo);
		homepage.seeRecord(addToDo);
		
		addToDo.setID(homepage.getRecordID(addToDo));
	}

	@Test(description = "Тест на редактирование записи", dependsOnMethods = {"testAddRecord"})
	public void testEditRecord() {
		editToDo.setID(addToDo.getID());
		
		homepage.goToPage();
		homepage.editRecord(addToDo, editToDo);
		homepage.seeRecord(editToDo);
		homepage.dontSeeRecord(editToDo);
	}

	@Test(description = "Тест на удаление", dependsOnMethods = {"testEditRecord"})
	public void testDeleteRecord() {
		homepage.goToPage();
		homepage.deleteRecord(editToDo);
		homepage.dontSeeRecord(editToDo);
	}
}
