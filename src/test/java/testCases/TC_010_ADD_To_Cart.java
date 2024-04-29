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

public class TC_010_ADD_To_Cart {


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

}
