package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	static public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass
	@Parameters( {"os","browser"})
	//public void setUp(String os,String br)throws IOException
	public void setUp(@Optional("defaultOS") String os, @Optional("chrome") String br) throws IOException {
	
			
		// loading properties file
		
		
		
		FileReader file = new FileReader(".//src//test//resources/config.properties");
		
		p = new Properties();
		p.load(file);
		
		
		// loading log4J file
		logger = LogManager.getLogger(this.getClass());
		
		
		
		//launching browser based on condition
		switch(br.toLowerCase())
		{
		case "chrome": driver = new ChromeDriver();break;
		case "edge": driver   = new EdgeDriver();break;
		default: System.out.println("No matching Browser");
		return;
		
		}
		
		//driver = new ChromeDriver();
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		
		
	}
	
	
	public void tearDown() {
	    try {
	        if (driver != null) {
	            driver.quit();
	        }
	    } catch (Exception e) {
	        System.out.println("Exception occurred during teardown: " + e.getMessage());
	    }
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}	
	
}

