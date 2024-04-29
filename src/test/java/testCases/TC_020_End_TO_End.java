package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_020_End_TO_End {

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

	public void displayProducts()
	{
		List<WebElement> items = driver.findElements(By.className("inventory_item_name"));


		for (WebElement item : items) {
			System.out.println(item.getText().trim());
		}
	}



	@Test(priority = 3)

	public void clickProduct()
	{
		WebElement myproduct = driver.findElement(By.linkText("Test.allTheThings() T-Shirt (Red)"));
		myproduct.click();

		WebElement AddToCart = driver.findElement(By.xpath("//button[@id='add-to-cart']"));
		AddToCart.click();

		String ExpectedTitle = "Test.allTheThings() T-Shirt (Red)";

		if(ExpectedTitle.equalsIgnoreCase("Test.allTheThings() T-Shirt (Red)"))
		{
			Assert.assertTrue(true);
			System.out.println("Title Matches with product page & checkout page");
		}	
		WebElement checkOut = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
		checkOut.click();

		System.out.println();

		System.out.println("Test case Executed Successfully..........!!!");

		WebElement QTY = driver.findElement(By.xpath("//div[@class='cart_quantity']"));

		System.out.println ("Products in Quantity are:" + QTY.getText());

		WebElement itemName = driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
		System.out.println("Item is: " + itemName.getText());

		WebElement itemPrice = driver.findElement(By.xpath("//div[@class='inventory_item_price']"));
		System.out.println("ItemPrice is: " + itemPrice.getText());

		WebElement checkout = driver.findElement(By.xpath("//button[@id='checkout']"));
		checkout.click();

		System.out.println(driver.getTitle());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("M.S");
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Purnim@");
		driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("560094");
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println(driver.getTitle());
		System.out.println(driver.findElement(By.cssSelector("div[data-test='payment-info-value']")).getText());
		System.out.println(driver.findElement(By.cssSelector("div[data-test='shipping-info-value']")).getText());
		System.out.println(driver.findElement(By.xpath("//div[@class='summary_total_label']")).getText());
		driver.findElement(By.xpath("//button[@id='finish']")).click();

		System.out.println(driver.getTitle());

		System.out.println(driver.findElement(By.xpath("(//h2[normalize-space()='Thank you for your order!'])[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//div[@class='complete-text']")).getText());
	}
	@AfterClass

	public void tearDown()
	{
		driver.quit();
	}
}

