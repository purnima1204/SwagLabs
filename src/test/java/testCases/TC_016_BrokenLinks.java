package testCases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_016_BrokenLinks {
	
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
	
	public void displayLinks() throws IOException
	{

		List <WebElement> mylinks = driver.findElements(By.tagName("a"));

		System.out.println(" Total Links:" + mylinks.size());

		int brokenlinks = 0;

		for(WebElement links : mylinks )
		{
			String hrefAttributevalue = links.getAttribute("href");

			if(hrefAttributevalue == null || hrefAttributevalue.isEmpty())
			{
				System.out.println("href attribute value is  empty");
				continue;
			}

			URL linkurl = new URL(hrefAttributevalue);

			HttpURLConnection conn = 	(HttpURLConnection)linkurl.openConnection();

			conn.connect();

			if(conn.getResponseCode() >= 400)
			{
				System.out.println(hrefAttributevalue+  " "  + "--->it is a broken Link");
				brokenlinks++;
			}
			else
			{
				System.out.println(hrefAttributevalue + " " + "--->Not a broken link");
			}

		}
		
		System.out.println(" Total broken links " + brokenlinks);
	}


}
