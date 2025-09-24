package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
public LoginPage(WebDriver driver){
	this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10s wait
	this.driver = driver;
}
By name = By.name("username");
By pass = By.name("password");
By lgbtn = By.className("oxd-button");
By ckh = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6");

public void login(String uname, String upass) {
	wait.until(ExpectedConditions.visibilityOfElementLocated(name));
	
	driver.findElement(name).sendKeys(uname);
	driver.findElement(pass).sendKeys(upass);
	driver.findElement(lgbtn).click();
}
public boolean verify() {
	wait.until(ExpectedConditions.visibilityOfElementLocated(ckh));
	return driver.findElement(ckh).isDisplayed();
	
	
}
}
