package testCases;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_Login_DDT extends BaseClass{



	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)

	public void verify_loginDDT(String Username,String Password,String expectedresult)
	{
		logger.info(" starting Tc_003 LoginDDT");

		try
		{

			LoginPage lp = new LoginPage(driver);

			lp.setUsername(Username);
			lp.setPassword(Password);
			lp.clickLogin();
			lp.clickMenu();
			lp.clickLogout();

			ProductsPage pp = new ProductsPage(driver);
			boolean mytarget = pp.display();

			if(expectedresult.equalsIgnoreCase("valid"))
			{
				if(mytarget == true)
				{
					lp.clickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}

			if(expectedresult.equalsIgnoreCase("Invalid"))
			{
				if(mytarget == true)
				{
					lp.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("Finished DDT");
	}
	
}


