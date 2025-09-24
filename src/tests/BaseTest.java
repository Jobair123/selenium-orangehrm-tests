package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import pages.LoginPage;

public class BaseTest {
WebDriver driver;
@BeforeClass
public void lgTest() {
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	LoginPage pg = new LoginPage(driver);
	pg.login("Admin", "admin123");
	
	Assert.assertTrue(pg.verify());
}
@AfterClass
public void tearDown() {
	
	driver.quit();
}
}
