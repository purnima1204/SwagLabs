package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_017_Display_Product_Name_Price_Description {

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

	public void AddProduct()
	{
		WebElement product = driver.findElement(By.linkText("Sauce Labs Backpack"));
		product.click();

		WebElement AddToCart = driver.findElement(By.xpath("//button[@id='add-to-cart']"));
		AddToCart.click();

		WebElement shoppingcart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
		shoppingcart.click();

		WebElement productDescription = driver.findElement(By.xpath("//div[@class='cart_list']"));
		System.out.println(productDescription.getText());

	}
	
}
