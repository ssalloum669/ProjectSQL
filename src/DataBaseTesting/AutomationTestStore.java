package DataBaseTesting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomationTestStore extends MyParameterClass {
	
  @BeforeTest
  public void AutomationSetup() throws SQLException {
 MySetupForSeleniumAndTestNG();
 }

  @BeforeMethod
 public void ThisShouldExecuteBeforeEveryTest() throws SQLException {
    	stmt=con.createStatement();
	 
 }
  @Test(priority=1)
	public void Read() throws SQLException, IOException, InterruptedException {
		rs=stmt.executeQuery(QueryToRead);
		while(rs.next()) {
			FirstName = rs.getString("contactFirstName").replace(" ", "_");
			LastName = rs.getString("contactLastName").replace(" ", "_"); 
		Email=FirstName+LastName+rand.nextInt(54533)+"@gmail.com";
		Address=rs.getString("addressLine1");
		ZipCode=rs.getString("postalCode");
		City=rs.getNString("city");
		}
  }
		
		@Test(priority=2)
		public void FillTheData() throws InterruptedException, IOException {
			//first and last name
			WebElement TheFirstName=driver.findElement(By.id("AccountFrm_firstname"));
	    	WebElement TheLastName=driver.findElement(By.id("AccountFrm_lastname"));
	    	TheFirstName.sendKeys(FirstName);
	    	TheLastName.sendKeys(LastName);
	    	//email
	    	WebElement MyEmail=driver.findElement(By.id("AccountFrm_email"));
	    	MyEmail.sendKeys(Email);
	    	//Address
	    	WebElement MyAddress=driver.findElement(By.id("AccountFrm_address_1"));
	    	MyAddress.sendKeys(Address);
	    	
	    	ScrollAndScreenShot(100,"1");
	    	
	    	//City
	    	WebElement MyCity=driver.findElement(By.id("AccountFrm_city"));
	    	MyCity.sendKeys(City);
			//Country
			WebElement SelectButton=driver.findElement(By.id("AccountFrm_country_id"));
	    	List<WebElement>Country=SelectButton.findElements(By.tagName("option"));
	    	Select MySelector=new Select(SelectButton);
	    	int MyRandomSelection=rand.nextInt(1,Country.size());
	    	MySelector.selectByIndex(MyRandomSelection);
	    	Thread.sleep(3000);
	    	//State
	    	WebElement StateSelect=driver.findElement(By.id("AccountFrm_zone_id"));
	    	List<WebElement>State=StateSelect.findElements(By.tagName("option"));
	    	Select MySelector2=new Select(StateSelect);
	    	int MyRandomSelection2=rand.nextInt(1,State.size());
	    	MySelector2.selectByIndex(MyRandomSelection2);
	    	//PostalCode
	    	WebElement Postalcode=driver.findElement(By.id("AccountFrm_postcode"));
	    	Postalcode.sendKeys(ZipCode);
	    	
	    	ScrollAndScreenShot(600,"2");
	    	
	 //Login and Password
	    	LoginName=FirstName+LastName+randomLoginNumber3;
	    	WebElement LoginNames=driver.findElement(By.id("AccountFrm_loginname"));
	    	LoginNames.sendKeys(LoginName);
	    	WebElement LoginPassword=driver.findElement(By.id("AccountFrm_password"));
	    	LoginPassword.sendKeys("Salloum@123");
	    	WebElement ConfirmPassword=driver.findElement(By.id("AccountFrm_confirm"));
	    	ConfirmPassword.sendKeys("Salloum@123");
	    	//privacyPolicy
	    	WebElement MyPrivacyButton=driver.findElement(By.id("AccountFrm_agree"));
	    	MyPrivacyButton.click();
	    	
	    	ScrollAndScreenShot(1000,"3");
	    	
	    	//continue
	    	WebElement Continue=driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click"));
	    	Continue.click();
	    	Boolean expectedValueForThecreatedAccount = true ; 
	    	Boolean ActualValue = driver.getPageSource().contains("Welcome back");
	    	Assert.assertEquals(ActualValue, expectedValueForThecreatedAccount);
	   	
    }
		
		
 
  
  @Test(priority=2, enabled=false)
	public void InsertToTheTable() throws SQLException {
	stmt.executeUpdate(QuerytoInsert);		
	}
  
  @Test(priority=3, enabled=false)
 	public void UpdateData() throws InterruptedException, SQLException {	
   	stmt.executeUpdate(QueryToUpdate);
   	Thread.sleep(3000);
  
   }
  @Test(priority=4, enabled=false)
	public void DeleteQuery() throws SQLException {
     stmt.executeUpdate(QueryToDelete);
		
		
	}
 
}
