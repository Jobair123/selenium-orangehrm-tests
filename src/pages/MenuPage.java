package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage {
	WebDriver driver;
	WebDriverWait wait;
public MenuPage(WebDriver d){
	
	driver = d;
	this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
}
By admin = By.linkText("Admin");
By PIM = By.linkText("PIM");
By Leave = By.linkText("Leave");
By Time = By.linkText("Time");
By Recruitment = By.linkText("Recruitment");
By MyInfo = By.linkText("My Info");
By Deshboard = By.linkText("Dashboard");
By chk = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6");

private void Click(By locator) {
	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
}

public void adminClick() {
	Click(admin);
}
public void pimClick() {
	Click(PIM);
}
public void leaveClick() {
	Click(Leave);
}
public void timeClick() {
	Click(Time);
}
public void recClick() {
	Click(Recruitment);
}
public void infoClick() {
	Click(MyInfo);
}
public void deshClick() {
	Click(Deshboard);
}

public boolean isDesh() {
	return wait.until(ExpectedConditions.visibilityOfElementLocated(chk)).isDisplayed();
}

}
