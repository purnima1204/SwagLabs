package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_011_Remove_Product_FromCart extends BaseClass {

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

	@Test(priority = 3)

	public void addTocart()
	{
		String title = "Products";
		String actualTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();

		if(title.equalsIgnoreCase(actualTitle)){

			Assert.assertEquals("Products",actualTitle,"TITLE MATCHES");
			System.out.println(actualTitle + "|| " + title);
			System.out.println("ActualTitle"+ "|| "  + "ExpectedTitle" + "==> " + "Matches");

			JavascriptExecutor js = (JavascriptExecutor)driver;

			WebElement product = driver.findElement(By.linkText("Sauce Labs Fleece Jacket"));

			js.executeScript("arguments[0].scrollIntoView(true);", product);

			product.click();

			WebElement AddtoCart = driver.findElement(By.xpath("//button[@id='add-to-cart']"));
			AddtoCart.click();

			WebElement shoppingCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
			shoppingCart.click();

			String ActualTitle = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
			String ExpectedTitle = "Sauce Labs Fleece Jacket";

			if(ExpectedTitle.equalsIgnoreCase(ActualTitle))
			{
				Assert.assertEquals("Sauce Labs Fleece Jacket",ActualTitle );
				System.out.println("Product in HomePage Matches with "
						+ "Product in AddToCart Page");

				System.out.println("Sauce Labs Fleece Jacket"+ " || " + ActualTitle); 	 

			}
		}
	}


	@Test(priority = 4)

	public void removeProductfromCart()
	{
		WebElement removeButton = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-fleece-jacket']"));
		removeButton.click();
		System.out.println("Product removed successfully");
	}

	@Test(priority = 5)

	public void validateCartempty()
	{
		WebElement shoppingCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
		shoppingCart.click();

		WebElement checkOut = driver.findElement(By.xpath("//button[@id='checkout']"));
		checkOut.click();

		driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("M.S");
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("superUser");
		driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("560094");

		WebElement continueButton = driver.findElement(By.xpath("//input[@id='continue']"));
		continueButton.click();

		System.out.println(driver.findElement(By.xpath("//div[normalize-space()='Price Total']")).getText());
		System.out.println(driver.findElement(By.cssSelector(".summary_subtotal_label")).getText());
		System.out.println(driver.findElement(By.xpath("//div[@class='summary_tax_label']")).getText());

		String minAmount = "Total: $0.00";
		String ProdPrice = "Total: $53.99";
		if(minAmount.equalsIgnoreCase("Total: $0.00"))
		{
			System.out.println("Product Not Added, CART IS EMPTY ...");
		}

		else if(minAmount.contentEquals(ProdPrice))
		{
			System.out.println("products are same");
		}
		
		else
		{
			System.out.println("No product has been added ");
		}
	}
}
