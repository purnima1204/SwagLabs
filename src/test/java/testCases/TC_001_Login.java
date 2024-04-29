package testCases;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.BasePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_001_Login extends BaseClass{

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
	
	
	@AfterClass
	
	public void tearDown()
	{
		driver.quit();
	}
	
	
	@Test(priority=1)
	
	public void verifyAccount()
	{
		LoginPage lp = new LoginPage(driver);
		
		lp.setUsername(p.getProperty("UserName"));
		lp.setPassword(p.getProperty("Password"));
		lp.clickLogin();
		logger.info("Entering valid userName & Password");
			
	}
	
	@Test(priority = 2) 
	
	public void verifyTitle()
	{
		// Verify the Title present in Home Page
		
		String  Pagetitle = "Swag Labs";
		Assert.assertEquals(Pagetitle, "Swag Labs");
		System.out.println("Title Matches ----->Login Successful");
		logger.info("******Successful Login************ ");		
		
	}
	
	@Test(priority = 3)
	
	public void verifyProducttitle()
	{
		String myprod = "Products";
		if(myprod.equalsIgnoreCase("Products"))
		{
			System.out.println(" ***** Products is present at LeftPane**** ");
		}
		else
		{
			System.out.println("Not found");
		}
		
	}
			
}


