package DataBaseTesting;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

public class CRUD {
	WebDriver driver=new ChromeDriver();
	Connection con;
	Statement stmt;
	ResultSet rs ;
	String Website="https://smartbuy-me.com/account/register";
	String FirstName;
	String LastName;

    
    @BeforeTest
    public void AutomationSetup() throws SQLException {
    	driver.get(Website);
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    	driver.manage().window().maximize();
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","abc123");    	
   }
    
    @Test(priority=1)
    public void UpdateTheData() throws SQLException, InterruptedException {
    	String Query="update customers set customerName='abu salloum' where customerNumber=103";
    	stmt=con.createStatement();
    	int RowUpdated=stmt.executeUpdate(Query);
    	System.out.println(RowUpdated);
    	Thread.sleep(3000);
    }
    
    @Test(priority=2)
    public void ReadTheDataInsideTheBrowser() throws SQLException {
    	String Query="select* from customers where customerNumber=103";
    	stmt=con.createStatement();
    rs=	stmt.executeQuery(Query);
    	System.out.println(rs);
    	while(rs.next()) {
    		FirstName=rs.getString("customerName");
    		LastName=rs.getString("contactLastName");	
    	}
    	WebElement TheFirstName=driver.findElement(By.id("customer[first_name]"));
    	WebElement TheLastName=driver.findElement(By.id("customer[last_name]"));
    	TheFirstName.sendKeys(FirstName);
    	TheLastName.sendKeys(LastName);
    }

  

  
    
    
    
    

}