package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_009_Verify_itemsCount_PricesCount {


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
	
   @AfterClass
	
	public void tearDown()
	{
		driver.quit();
	}

	@Test(priority = 2)

	public void verifyitemsCount$PricesCount()
	{
		List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
		List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));

		if(items.size() != prices.size())
		{
			System.out.println("Count not matches");
			return;
		}

		for(int i = 0; i < items.size(); i++) 
		{
			WebElement item = items.get(i);
			WebElement price = prices.get(i);

			String itemName = item.getText().trim();
			String itemPrices = price.getText().trim();

			System.out.println(itemName + "---> " + itemPrices + " " +  "- -> " + "Count with item and product");
			
		}
		
		System.out.println("=========>COUNT MATCHES<===========");

	}
}

