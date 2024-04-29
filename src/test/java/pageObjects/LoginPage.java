package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{

	public LoginPage(WebDriver driver)
	{
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='user-name']")    WebElement Username;
	
	@FindBy(xpath="//input[@id='password']")     WebElement password;
	
	@FindBy(xpath="//input[@id='login-button']") WebElement loginbutton;
	
	@FindBy(xpath = "//button[@id='react-burger-menu-btn']") WebElement menu;
	
	@FindBy(xpath = "//a[@id='logout_sidebar_link']") WebElement logout;
	
	
	

	public void setUsername(String username) {
		Username.sendKeys(username);
	}

	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void clickLogin() {
		loginbutton.click();
	}
	
	public void clickMenu() {
		menu.click();
	}
	
	
	public void clickLogout() {
		logout.click();
	}

}
