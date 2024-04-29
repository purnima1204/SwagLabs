package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_005_Scrolling extends BaseClass{
	
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
	
	
	@Test(priority = 1)

	public void login()
	{
LoginPage lp = new LoginPage(driver);
		
		lp.setUsername(p.getProperty("UserName"));
		lp.setPassword(p.getProperty("Password"));
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		
         JavascriptExecutor js = (JavascriptExecutor)driver;

		js.executeScript("window.scrollBy(0,4000)", "");
		
		logger.info("-----TC_005_Scrolled Successfully----");
	}
}

