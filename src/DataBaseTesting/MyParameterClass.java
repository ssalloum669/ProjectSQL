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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyParameterClass {
	WebDriver driver=new ChromeDriver();
	String Website="https://automationteststore.com/index.php?rt=account/create";
	Connection con;
	Statement stmt;
	ResultSet rs ;	
	String FirstName;
	String LastName;
	String Email;
	String Address;
	String ZipCode;
	String City;
	String LoginName;
	Date TimeStamp= new Date();
    Random rand=new Random();
    int randomLoginNumber = rand.nextInt(32371);
	int randomLoginNumber2 = rand.nextInt(332171);
	String randomLoginNumber3 =  Integer.toString(randomLoginNumber*randomLoginNumber2);

String[]CustomerNumber= {"114","119","121","124"};
int MyRandomCustomerNumber=rand.nextInt(CustomerNumber.length);
String MySelection=CustomerNumber[MyRandomCustomerNumber];
String QueryToRead="select* from customers where customerNumber="+MySelection;
String QueryToDelete="delete from customers where customerNumber=10002";
String QuerytoInsert = "INSERT INTO customers\r\n"
		+ "(customerNumber, customerName, contactLastName, contactFirstName, phone,\r\n"
		+ " addressLine1, addressLine2, city, state, postalCode, country,\r\n"
		+ " salesRepEmployeeNumber, creditLimit)\r\n"
		+ "VALUES\r\n"
		+ "(10002, 'mahmoud', 'Schmitt', 'Carine', '40.32.2555',\r\n"
		+ " '54, rue Royale', NULL, 'Nantes', NULL, '44000', 'France', 1370, 21000);\r\n"
		+ "\r\n"
		+ "";
String QueryToUpdate="update customers set customerName='Automation Salloum' where customerNumber=10002";
	
  public void MySetupForSeleniumAndTestNG() throws SQLException {
	   driver.get(Website); 
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	   driver.manage().window().maximize();
	   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","abc123");  
	
	
}

	public void ScrollAndScreenShot(int theValueWhereToStop,String ScreenShotOrder) throws IOException, InterruptedException {
		
JavascriptExecutor js =(JavascriptExecutor)driver;
js.executeScript("window.scrollTo(0,arguments[0])",theValueWhereToStop);
Thread.sleep(1000);
TakesScreenshot ts=(TakesScreenshot)driver;
File MyScreenShotFile=ts.getScreenshotAs(OutputType.FILE);
String FileName=TimeStamp.toString().replace(":", "-")+ScreenShotOrder;
FileUtils.copyFile(MyScreenShotFile, new File("src/ScreenShotAuto/"+FileName+".jpg"));


		
		 
	}

}
