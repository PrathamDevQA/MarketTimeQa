package dateOfBirth;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectFromDropDown {
	
	public static WebDriver driver;
	public static String browser = "Chrome";
	
	public static void main(String[] args) {
		
	setUP();
	
	selectBootsStrape();
	
		
		
	}
	
	public static void setUP() {
		
		if(browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equals("FF")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browser.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.get("https://www.automationtestinginsider.com/2019/12/bootstrap-dropdown-example_12.html");
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	}

	public static void selectBootsStrape() {
		
		driver.findElement(By.id("bootstrapmenu")).click();
		List<WebElement> opt = driver.findElements(By.xpath("//ul[@class='dropdown-menu']//li/a"));
		for(WebElement ele : opt) {
			String valu  =ele.getText();
			System.out.println(valu);
			
			if(valu.equalsIgnoreCase("Contact Us")) {
				ele.click();
				break;
			}
		}
		
		
		
	}

}
