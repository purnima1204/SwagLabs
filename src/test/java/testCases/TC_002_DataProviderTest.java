package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_002_DataProviderTest extends BaseClass {
	
	
public WebDriver driver;
	
	@BeforeClass
	
	public void setUp()
	{
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
	}
	
	
	
    @Test(dataProvider= "dp")
	
	public void verifyAccount(String username,String pwd)
	{
		LoginPage lp = new LoginPage(driver);
		
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click(); // menu burger
		driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click(); // Logout
		
		logger.info("**********DataProvider Method Successfully Executed***********");
			
	}
	
@AfterClass
	
	public void tearDown()
	{
		driver.quit();
	}
	

@DataProvider(name = "dp",indices= {0,4})

      String [][] loginData()
          {
        	  String [][] data = {
        			             
        			  {"standard_user","secret_sauce"}, // pass
        			  {"visual_user","secret_sauce"},   // pass
        			  {"error_user","secret_sauce"},    // pass
        			  {"performance_glitch_user","secret_sauce"}, // pass
        			  {"purnima","Admin"}, // Fail
        			  
        	          };
        	  
			return data;
          }
}
