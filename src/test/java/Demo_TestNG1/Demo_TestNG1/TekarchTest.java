package Demo_TestNG1.Demo_TestNG1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TekarchTest {
	public static WebDriver driver=null;
	@BeforeTest
	public void launchBrowser() {
		BrowserUtility.launchBroswer("ch");
		driver=BrowserUtility.driver;
	}
	@Test
	public void validLogin() {
		driver.findElement(By.id("email_field")).sendKeys("admin123@gmail.com");
		driver.findElement(By.id("password_field")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[contains(text(),'Login to Account')]")).click();
		
	}
	@Test
	public void invalidLogin() {
		driver.findElement(By.id("email_field")).sendKeys("admin123@gmail.com");
		driver.findElement(By.id("password_field")).sendKeys("admin");
		driver.findElement(By.xpath("//button[contains(text(),'Login to Account')]")).click();
	}
	@AfterMethod
	public void logout() {
		driver.get("https://qa-tekarch.firebaseapp.com/");
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("email_field"))));
	}
	@AfterTest
	public void quitBrowser() {
		BrowserUtility.quitBrowser();
	}
	

}
