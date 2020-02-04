package myTestProject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import myTestProject.config.TestNgTestBase;
import myTestProject.models.ToDoModel;
import myTestProject.pages.HomePage;

public class DeleteAllTests extends TestNgTestBase {

	private HomePage homepage;
	private ToDoModel record1;
	private ToDoModel record2;

	@BeforeMethod
	public void beforeDeleteAllTests() {
		homepage = new HomePage();

		record1 = new ToDoModel("DELETE", "secondary");
		record2 = new ToDoModel("Delete All", "important");

		homepage.goToPage();
		homepage.addRecord(record1);
		homepage.addRecord(record2);
	}

	@Test(description = "Тест на удаление всех записей с отменой удаления")
	public void testCancelDeleteAll() {
		homepage.goToPage();
		homepage.clickDeleteAll();
		homepage.cancelDeleteAll();
		homepage.seeRecord(record1);
		homepage.seeRecord(record2);
	}

	@Test(description = "Тест на удаление всех записей с подтверждением удаления")
	public void testConfirmDeleteAll() {
		homepage.goToPage();
		homepage.clickDeleteAll();
		homepage.confirmDeleteAll();
		homepage.dontSeeRecord(record1);
		homepage.dontSeeRecord(record2);
	}
}
