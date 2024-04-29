package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_012_StaticDropdown_Name_Z_A extends BaseClass  {
	
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



	@Test(priority=0)

	public void verifyAccount()
	{
		LoginPage lp = new LoginPage(driver);

		lp.setUsername(p.getProperty("UserName"));
		lp.setPassword(p.getProperty("Password"));
		lp.clickLogin();
		logger.info("Entering valid userName & Password");

	}

	@Test(priority = 1) 

	public void verifyTitle()
	{
		// Verify the Title present in Home Page

		String  Pagetitle = "Swag Labs";
		Assert.assertEquals(Pagetitle, "Swag Labs");
		System.out.println("Title Matches ----->Login Successful");
		logger.info("******Successful Login************ ");		

	}
	
	@Test(priority = 2)
	
	public void staticDropdown()
	{
		WebElement staticDropdown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		staticDropdown.click();
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByValue("za");
		  JavascriptExecutor js = (JavascriptExecutor)driver;

			js.executeScript("window.scrollBy(0,4000)", "");

	}
   @Test(priority = 3)
	
	public void displayProducts()
	{
		List<WebElement> items = driver.findElements(By.className("inventory_item_name"));

		
		for (WebElement item : items) {
            System.out.println(item.getText().trim());
            
        }
		System.out.println("Sorted from Z TO A");
	}
	
}
