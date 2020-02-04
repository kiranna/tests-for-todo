package myTestProject;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import myTestProject.config.TestNgTestBase;
import myTestProject.models.ToDoModel;
import myTestProject.pages.HomePage;

public class SortTests extends TestNgTestBase {

	private HomePage homepage;
	private ToDoModel record1;
	private ToDoModel record2;
	private ToDoModel record3;
	
	private List<Integer> idList = new ArrayList<>();
	private List<String> titleList = new ArrayList<>();
	
	
	@BeforeClass
	public void beforeSortTests() {
		homepage = new HomePage();

		record1 = new ToDoModel("ggg Filter_test", "secondary");
		record2 = new ToDoModel("aaa test record", "secondary");
		record3 = new ToDoModel("ccc test for filters", "important");

		homepage.goToPage();
		homepage.addRecord(record1);
		homepage.addRecord(record2);
		homepage.addRecord(record3);
		
		idList.add(Integer.parseInt(homepage.getRecordID(record1)));
		idList.add(Integer.parseInt(homepage.getRecordID(record2)));
		idList.add(Integer.parseInt(homepage.getRecordID(record3)));
		
		titleList.add(record1.getTitle());
		titleList.add(record2.getTitle());
		titleList.add(record3.getTitle());
	}
	
	@Test(description = "Тест на сортировку ID по возрастанию")
	public void testSortIDByAsc() {
		idList.sort(Comparator.naturalOrder());
		
		homepage.goToPage();
		homepage.sortIDByAsc();
		List<Integer> idListInTable = homepage.getIDFromAllRecords();
		
		assertTrue(idList.equals(idListInTable), 
				"Фактический порядок не соответствует ожидаемому. "
				+ "\nФактический: " + Arrays.toString(idListInTable.toArray())
				+ "\nОжидаемый: " + Arrays.toString(idList.toArray()));
	}
	
	@Test(description = "Тест на сортировку ID по убываню")
	public void testSortIDByDesc() {
		idList.sort(Comparator.reverseOrder());
		
		homepage.goToPage();
		homepage.sortIDByDesc();
		List<Integer> idListInTable = homepage.getIDFromAllRecords();
		
		assertTrue(idList.equals(idListInTable), 
				"Фактический порядок не соответствует ожидаемому. "
				+ "\nФактический: " + Arrays.toString(idListInTable.toArray())
				+ "\nОжидаемый: " + Arrays.toString(idList.toArray()));
	}
	
	@Test(description = "Тест на сортировку названия Todo по возрастанию")
	public void testSortTitleDByAsc() {
		titleList.sort(Comparator.naturalOrder());
		
		homepage.goToPage();
		homepage.sortTitleByAsc();
		List<String> titleListInTable = homepage.getTitleFromAllRecords();
		
		assertTrue(titleList.equals(titleListInTable), 
				"Фактический порядок не соответствует ожидаемому. "
				+ "\nФактический: " + Arrays.toString(titleListInTable.toArray())
				+ "\nОжидаемый: " + Arrays.toString(titleList.toArray()));
	}
	
	@Test(description = "Тест на сортировку названия Todo по убываню")
	public void testSortTitleByDesc() {
		titleList.sort(Comparator.reverseOrder());
		
		homepage.goToPage();
		homepage.sortTitleByDesc();
		List<String> titleListInTable = homepage.getTitleFromAllRecords();
		
		assertTrue(titleList.equals(titleListInTable), 
				"Фактический порядок не соответствует ожидаемому. "
				+ "\nФактический: " + Arrays.toString(titleListInTable.toArray())
				+ "\nОжидаемый: " + Arrays.toString(titleList.toArray()));
	}
}
