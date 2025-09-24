package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.MenuPage;

public class MenuTest extends BaseTest {

	@Test
	public void tc02() {
		
		MenuPage pg = new MenuPage(driver);
		pg.adminClick();
		pg.deshClick();
		Assert.assertTrue(pg.isDesh());
	}

}
