package DataBaseTesting;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyDatabase {
	WebDriver driver=new ChromeDriver();
	String Website="https://automationteststore.com/";
	Connection con;
	Statement stmt;
	ResultSet rs ;	
	String FirstName;
	String LastName;
	Date TimeStamp= new Date();
Random rand=new Random();


	
	@BeforeTest
	public void MySetup() throws SQLException {
		driver.get(Website);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","abc123");
		System.out.println(TimeStamp);
	}
	
	
	
	
	@Test(priority=3)
	public void UpdateData() throws InterruptedException, SQLException {
		
		String QueryToUpdate="update customers set customerName='Automation Salloum' where customerNumber=10001";
    	stmt=con.createStatement();
    	int RowUpdated=stmt.executeUpdate(QueryToUpdate);
    	System.out.println(RowUpdated);
    	Thread.sleep(3000);

		
	
		
	}

	@Test(priority=2)
	public void NewCostumer() {
	driver.navigate().to("https://automationteststore.com/index.php?rt=account/login");
	WebElement Continue=driver.findElement(By.cssSelector(".btn.btn-orange.pull-right"));
	Continue.click();	
	}
	
	@Test(priority=4)
	public void Read() throws SQLException, IOException {
	//	String[]CustomerNumber= {"114","119","121","124"};
		//int MyRandomCustomerNumber=rand.nextInt(CustomerNumber.length);
		//String MySelection=CustomerNumber[MyRandomCustomerNumber];
	//	String Query="select* from customers where customerNumber="+MySelection;
		String Query="select* from customers where customerNumber=10001";
		stmt=con.createStatement();
		rs=stmt.executeQuery(Query);
		while(rs.next()) {
			
		FirstName=rs.getString("customerName");	
		LastName=rs.getString("contactLastName"); 
		}
		
		WebElement TheFirstName=driver.findElement(By.id("AccountFrm_firstname"));
    	WebElement TheLastName=driver.findElement(By.id("AccountFrm_lastname"));
    	TheFirstName.sendKeys(FirstName);
    	TheLastName.sendKeys(LastName);
    	MyScreenShot();
    	
		 

	}
		@Test(priority=1)
		public void InsertToTheTable() throws SQLException {
			String Query = "INSERT INTO customers\r\n"
					+ "(customerNumber, customerName, contactLastName, contactFirstName, phone,\r\n"
					+ " addressLine1, addressLine2, city, state, postalCode, country,\r\n"
					+ " salesRepEmployeeNumber, creditLimit)\r\n"
					+ "VALUES\r\n"
					+ "(10001, 'mahmoud', 'Schmitt', 'Carine', '40.32.2555',\r\n"
					+ " '54, rue Royale', NULL, 'Nantes', NULL, '44000', 'France', 1370, 21000);\r\n"
					+ "\r\n"
					+ "";
			stmt=con.createStatement();
	    	stmt.executeUpdate(Query);
			
		}
		
		@Test(priority=5)
		public void DeleteQuery() throws SQLException {
			String Query="delete from customers where customerNumber=10001";
			stmt=con.createStatement();
	    	stmt.executeUpdate(Query);
			
			
		}
		
		@Test
		public void MyScreenShot() throws IOException {
			TakesScreenshot ts=(TakesScreenshot)driver;
			File MyScreenShotFile=ts.getScreenshotAs(OutputType.FILE);
			String FileName=TimeStamp.toString().replace(":", "-");
			FileUtils.copyFile(MyScreenShotFile, new File("src/Screenshot/"+FileName+".jpg"));
			
		}

		
		
	
	
	
	
	@AfterTest
	public void closer() {
		
		
	}
}
