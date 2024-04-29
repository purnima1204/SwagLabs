package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

	public ProductsPage(WebDriver driver) {
		super(driver);
		
	}
	
	
	@FindBy(xpath = "//span[@class='title']") WebElement products;
	
	@FindBy(xpath = "//a[@id='logout_sidebar_link']") WebElement logout;
	
	
	public boolean display()
	{
		return products.isDisplayed();
	}
	
	public void clickLogout()
	{
		logout.click();
	}

}
