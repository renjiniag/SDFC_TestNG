package Demo_TestNG1.Demo_TestNG1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtility {

	public static WebDriver driver = null;
	public static void launchBroswer(String browser) {
		if(browser.startsWith("ch")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.startsWith("ed")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();			
		}
		else {
			System.out.println("Wrong Browser Selected");
		}
		if(driver!=null) {
			driver.get("https://qa-tekarch.firebaseapp.com/");
			driver.manage().window().maximize();
		}
	}
	public static void loginBrowser() {
		driver.findElement(By.id("email_field")).sendKeys("admin123@gmail.com");
		driver.findElement(By.id("password_field")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[contains(text(),'Login to Account')]")).click();
	}
	public static void logoutBrowser() {
		WebElement logout=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		if(logout.isDisplayed()) {
			logout.click();
		}
	}
	public static void quitBrowser() {
		driver.quit();
	}

}
