package myTestProject.config;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for TestNG-based test classes
 */
public class TestNgTestBase extends BaseVariables{

	protected static URL gridHubUrl = null;

	protected static Capabilities capabilities;

	@BeforeSuite
	public void initTestSuite() throws IOException {
		SuiteConfiguration config = new SuiteConfiguration();
		baseUrl = config.getProperty("site.url");
		if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
			gridHubUrl = new URL(config.getProperty("grid.url"));
		}
		capabilities = config.getCapabilities();
		
		driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
		wait = new WebDriverWait(driver, 5);
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		WebDriverPool.DEFAULT.dismissAll();
	}
}
