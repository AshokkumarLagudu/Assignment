package com.ashok_Assignment.utils;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
	
	private static WebDriver driver;
	
	//WebDriver setup
	public static WebDriver getDriver(String browserName,String url,String driverLocationPath){
		
		// Chrome Browser setup
		if(browserName.equals("chrome")){
			
			System.setProperty("webdriver.chrome.driver", driverLocationPath);
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			driver = new ChromeDriver(capabilities);
		
			//FireFox Brower setup
		}else if(browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", driverLocationPath);
			driver=new FirefoxDriver();

			//InternetExplorer Browser setup
		}else if(browserName.equals("IE")){
			
			System.setProperty("webdriver.ie.driver", driverLocationPath);
			driver=new InternetExplorerDriver();
			
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(UtilClass.pageloadTimeout, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().deleteAllCookies();
		return driver;
	}

}
