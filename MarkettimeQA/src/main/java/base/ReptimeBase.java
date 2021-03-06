package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;

public class ReptimeBase {

	public static Properties props;
//	public static WebDriver driver;
	
	//Declare ThreadLocal Driver
	public static ThreadLocal<RemoteWebDriver>driver= new ThreadLocal<RemoteWebDriver>();
	
	@BeforeSuite
	public void beforeSuite() {
		DOMConfigurator.configure("log4j.xml");
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}

	public String browser;
	
	public LoginPage loginpage;
	

	@BeforeClass
	public void setUP() {

		loadConfig();
	
		browser = props.getProperty("browser");

		if (browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
			driver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver()); 
		} else if (browser.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver.set(new InternetExplorerDriver()); 
		} else if (browser.equalsIgnoreCase("Safari")) {
			WebDriverManager.safaridriver().setup();
			driver.set(new SafariDriver());
		}

//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		loginpage = new LoginPage();

	}

	@BeforeMethod
	public void openURL() {
		getDriver().get(props.getProperty("URL"));
	}

	@AfterClass
	public void tearsDown() {
		getDriver().close();
	}

	public void loadConfig() {

		try {
			props = new Properties();

			FileInputStream inputFile = new FileInputStream(
					"C:\\MarketTimeQa\\MarkettimeQA\\Configrations\\config.properties");

			props.load(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
