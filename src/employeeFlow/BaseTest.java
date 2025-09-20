package employeeFlow;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class BaseTest {
	WebDriver driver;
	 WebDriverWait wait;
	 String ids;
	  
  @Parameters({"url","username","password"})
  @BeforeClass
  public void login(String url, String username, String password) {
	       setup(url);
	       
	       wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(username);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(password);
     WebElement lgbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")));
     lgbtn.sendKeys(Keys.ENTER);
      String Actual_title = driver.getTitle();
      Assert.assertEquals(Actual_title, "OrangeHRM");
      
	  
  }
  
  @Test(priority=1)
  public void navigate_to_pim() {
	  
	  WebElement pim = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a/span")));
	  pim.click();
	  
	 String Actual = driver.getCurrentUrl();
	 String Exp = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";
	 Assert.assertEquals(Actual, Exp,"Unable to navigate to PIM");
	  
	  
  }
 @Parameters("id_no")
 @Test(priority=2)
 public void search(String id_no) {
	 WebElement id = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input")));
	 id.sendKeys(id_no);
	 WebElement src = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")));
	 src.sendKeys(Keys.ENTER);
	 WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-card")));
	 Assert.assertTrue(result.isDisplayed());
	 
 }
 @Test(priority=3,dataProvider="getEmployeeData")
 public void add_employee() {
	 driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button")).sendKeys(Keys.ENTER);
	 
	WebElement name =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
	name.sendKeys("Bamboo");
	WebElement middlename =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("middleName")));
	middlename.sendKeys("ddd");
	WebElement lastname =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastName")));
	lastname.sendKeys("kkk");
	WebElement uid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input")));
	 ids = uid.getAttribute("value");
	System.out.println(ids);
	
	WebElement togglebtn = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label/span"));
	togglebtn.click();
	//add user details
	//upload image
	File uploadFile = new File("C:\\Users\\jobai\\Downloads\\emp.jpg");

	// Locate the hidden file input (not the button)
	WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));

	// Upload the file directly
	fileInput.sendKeys(uploadFile.getAbsolutePath());

	  //for unique username  
	String uniqueUsername = "MrXvxcsdf_" + System.currentTimeMillis();

	WebElement username =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/div[2]/input")));
	username.sendKeys(uniqueUsername);
	WebElement password =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input")));
	password.sendKeys("pop1234323###aqA");
	WebElement c_password =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input")));
	c_password.sendKeys("pop1234323###aqA");
	
	WebElement submit =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]")));
	submit.sendKeys(Keys.ENTER);
	
	WebElement getval =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[1]/div[1]/div/div[2]/input")));
    String val = getval.getAttribute("value");
	Assert.assertEquals(val, ids);
	
	WebElement emp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/a")));
	emp.sendKeys(Keys.ENTER);
	//search for verify
	WebElement id = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input")));
	 id.sendKeys(ids);
	 WebElement src = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")));
	 src.sendKeys(Keys.ENTER);
	 WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-card")));
	 Assert.assertTrue(result.isDisplayed());
	
	 
 }
 
 @Test(priority=4)
  public void edit_employee() throws InterruptedException {
	   //Click on employee list
		WebElement emp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/a")));
		emp.sendKeys(Keys.ENTER);
		//search for verify
		WebElement ids = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input")));
		 ids.sendKeys("0313");
		 WebElement src = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")));
		 src.sendKeys(Keys.ENTER);
		 WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-card")));
		 Assert.assertTrue(result.isDisplayed());
		 
		 WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[9]/div/button[1]")));
		 edit.click();
		//edit
		WebElement fname =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
		Thread.sleep(1000);
		fname.sendKeys(Keys.chord(Keys.CONTROL, "a")); // Select all existing text
		fname.sendKeys("Mr Badhsa");
		WebElement lname =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastName")));
		Thread.sleep(1000);
		lname.sendKeys(Keys.chord(Keys.CONTROL, "a")); // Select all existing text
		lname.sendKeys("Hauque");
		WebElement date = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[2]/div/div[2]/div/div/input")));
		Thread.sleep(1000);
		date.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		date.sendKeys("2025-10-23");
		WebElement nationality = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div/div[1]")));
		nationality.sendKeys(Keys.ENTER);
		 // Wait for all options to load
		List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']//span")));
		
		//choose nationality
		String country_nat = "Belarusian";
		for(WebElement option:options) {
			if(option.getText().equals(country_nat)) {
				option.click();
				break;
			}
		}
		
		WebElement gender = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[2]/div/div[2]/div[1]/div[2]/div/label")));
		gender.click();
		
		WebElement save = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/button")));
		save.submit();
		
		// Wait for success message
		WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast-content')]")));
		// Assert the message text
		String messageText = successMsg.getText();
		Assert.assertTrue(messageText.contains("Successfully Updated"), "Employee edit was not saved successfully.");
  }
 @Test(priority=5)
 public void delete() throws InterruptedException {
	 //Click on employee list
		WebElement emp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/a")));
		emp.sendKeys(Keys.ENTER);
		
		WebElement id = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input")));
		id.sendKeys(ids);
		Thread.sleep(5000);
//		WebElement jtl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[6]/div/div[2]/div/div/div[1]")));
//	    jtl.sendKeys(Keys.ENTER);
//	    
//	    List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']//span")));
//	    for(WebElement option:options) {
//	    	if(option.getText().equals("Customer Success Manager")) {
//	    		option.click();
//	    		break;
//	    	}
//	    }
	    WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")));
	    search.click();
	    
	    WebElement delbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[i[contains(@class, 'bi-trash')]]\n"+ "")));
	    delbtn.sendKeys(Keys.ENTER);
	    
	    WebElement del = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]")));
	    del.sendKeys(Keys.ENTER);
	    
	 // Wait for success message
	 		WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	 		// Assert the message text
	 		String messageText = successMsg.getText();
	 		Assert.assertTrue(messageText.contains("Successfully Deleted"), "Employee edit was not saved successfully.");
	    
 }


  public void setup(String url) {
	  driver = new EdgeDriver();
	  driver.get(url);
	  driver.manage().window().maximize();
	  
  }

  @AfterClass
  public void tearDown() {
	   // Click on the profile icon
	    WebElement profileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='app']/div[1]/div[1]/header/div[1]/div[3]/ul/li/span")));
	         profileIcon.click();
	      // Wait for the logout option to be visible and clickable
	         List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='app']/div[1]/div[1]/header/div[1]/div[3]/ul/li/ul/li")));
	  for(WebElement option:options) {
		  if(option.getText().trim().equals("Logout")) {
			  option.click();
			  break;
		  }
	  }
	  
	  	  driver.close();
  }
  
 


}
