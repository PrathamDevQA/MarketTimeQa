package dateOfBirth;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectDateOfBirth {

	public WebDriver driver;
	public String browser = "Chrome";
	
	public void setUp() {
		
		if (browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equals("FF")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.get("https://www.automationtestinginsider.com/2019/08/student-registration-form.html");
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	public void DropDown() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@name='First_Name']")).sendKeys("Manmit");
		driver.findElement(By.xpath("//input[@name='Last_Name']")).sendKeys("Gamit");
		WebElement bDay = driver.findElement(By.id("Birthday_Day"));
		WebElement bMonth = driver.findElement(By.id("Birthday_Month"));
		WebElement bYear = driver.findElement(By.id("Birthday_Year"));
		
		Select selectDay = new Select(bDay);
		Select selectMonth = new Select(bMonth);
		Select selectYear = new Select(bYear);
		
		selectDay.selectByIndex(22);
		Thread.sleep(2000);
		selectMonth.selectByValue("November");
		Thread.sleep(2000);
		selectYear.selectByVisibleText("1992");
		Thread.sleep(2000);
		
		
		List<WebElement> month = selectMonth.getOptions();
		
		System.out.println("Total Month:- "+month.size());
		for (WebElement ele:month) {
			System.out.println(ele.getText());
		}
	}
	
	public void tearDown() {
		driver.close();
	}
	public static void main(String[] args) throws InterruptedException {
		SelectDateOfBirth obj = new SelectDateOfBirth();
		obj.setUp();
		obj.DropDown();
		obj.tearDown();
		
	}
}
