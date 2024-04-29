package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import testBase.BaseClass;

public class TC_006_ScrollPage_ClickOnProduct extends BaseClass{
	
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
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		
	}
	
	@Test(priority = 2)
	
	public void verifyTitleClickProduct()
	{
		
         JavascriptExecutor js = (JavascriptExecutor)driver;
         
         WebElement product = driver.findElement(By.linkText("Test.allTheThings() T-Shirt (Red)"));
         
         js.executeScript("arguments[0].scrollIntoView(true);", product);
         
         product.click();
         
         System.out.println(driver.getCurrentUrl());
          
         String productName = "Test.allTheThings() T-Shirt (Red)";
         
         if(productName.equalsIgnoreCase("Test.allTheThings() T-Shirt (Red)"))
         {
        	 Assert.assertEquals(productName, "Test.allTheThings() T-Shirt (Red)","TitleMatches");
        	 System.out.println("Title Matches");
         }
         else
         {
        	 Assert.fail("Title Not Matched");
         }

 }
	
}
	
