package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TC_018_Headless_Browser_Testing {
	
	public WebDriver driver;
	
	
	@BeforeClass
	
	public void setUp()
	{

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
       
        driver = new ChromeDriver(options);
    }

	
	@Test(priority =1)
	
	public void verifyTitle()
	{
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		ChromeOptions options= new ChromeOptions();
		options.addArguments("headless");
		driver=new ChromeDriver(options);
		driver.get("https://www.saucedemo.com/");
		System.out.println("Title is: " +driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Swag Labs");
		System.out.println("Test Pass on HeadLess Mode");
		System.out.println(driver.getTitle() + " || " + "Swag Labs");
	}

}
