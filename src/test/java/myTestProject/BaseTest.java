package myTestProject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import myTestProject.Config.TestNgTestBase;
import myTestProject.pages.HomePage;

public class BaseTest extends TestNgTestBase {

	private HomePage homepage;

	@BeforeMethod
	public void initPageObjects() {
		homepage = new HomePage();
	}

	@Test(description = "Тест на открытие страницы")
	public void testCheckPage() {
		homepage.goToPage();
	}
}
