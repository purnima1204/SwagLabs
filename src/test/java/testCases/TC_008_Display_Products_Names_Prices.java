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

public class TC_008_Display_Products_Names_Prices {

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


	@Test(priority = 1)

	public void login()
	{
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@id='login-button']")).click();

	}

	@Test(priority = 2)

	public void displayProductsNames$Prices()
	{
		List<WebElement> items = driver.findElements(By.className("inventory_item_name"));


		for (WebElement item : items) {
			System.out.println(item.getText().trim());
		}
		
		System.out.println();
		
		
		System.out.println("------Prices-----");


			List<WebElement> itemprices = driver.findElements(By.className("inventory_item_price"));

			for(WebElement prices:itemprices)
			{
				System.out.println( prices.getText().trim() );
			}

		}
	}
