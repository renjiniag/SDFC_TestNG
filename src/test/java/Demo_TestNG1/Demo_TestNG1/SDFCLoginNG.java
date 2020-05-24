package Demo_TestNG1.Demo_TestNG1;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SDFCLoginNG {
	public static WebDriver driver=null;
	static ExtentReports reports;
	static ExtentTest logger;
	static WebDriverWait wait;
	
	public static String[][] readExcel(String excelPath,String sheetName) throws IOException{
		//Creating reference to open xls file
		File file= new File(excelPath);
		FileInputStream sFile=new FileInputStream(file);
		HSSFWorkbook book=new HSSFWorkbook(sFile);
		HSSFSheet sheet=book.getSheet(sheetName); 
		int rowCount=sheet.getLastRowNum()+1;
		//System.out.println(rowCount);
		int colCount=sheet.getRow(0).getLastCellNum();
		//System.out.println(colCount);
		String[][] testData=new String[rowCount][colCount];
		for(int i=0;i<rowCount;i++) {
			for(int j=0;j<sheet.getRow(i).getLastCellNum();j++) {
				testData[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				//System.out.println(testData[i][j]);
			}
		}
		book.close();
		return testData;
	}
	
	public static void enterTextBox(WebElement textBox,String input,String textbox_name) {
		if(textBox.isDisplayed()==true) {
			if(textBox.isEnabled()==true) {
				textBox.sendKeys(input);
				if(textBox.getAttribute("value").equals(input)) {
					logger.log(LogStatus.PASS,input+" "+textbox_name );
				}
				else {
					logger.log(LogStatus.FAIL,input+" "+textbox_name);
				}
			}
			else
			logger.log(LogStatus.FAIL,textbox_name+" not enabled");
		}
		else
		logger.log(LogStatus.FAIL,textbox_name+" not displayed");
	}
	
	public static void clickButton(WebElement button,String buttonName) {
		if(button.isDisplayed()==true) {
			if(button.isEnabled()==true) {
				button.click();
				logger.log(LogStatus.PASS, buttonName+" clicked");
			}
			else {
				logger.log(LogStatus.FAIL, buttonName+" not enabled" );
			}
		}
		else {
			logger.log(LogStatus.FAIL, buttonName+" not displayed" );
		}
	}
	
	@BeforeTest
	public void report() {
		String date=new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		String sPath=System.getProperty("user.dir")+"//ExtentReport//Sample_"+date+".html";
		reports=new ExtentReports(sPath);
		
		
	}
	@BeforeMethod
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		 wait = new WebDriverWait(driver,30);
	}
	
	@Test
	public void Testcase1() throws IOException {
		logger=reports.startTest("Testcase1 STARTED");
		String excelPath=System.getProperty("user.dir")+"/src/main/java/resources/SDFC.xls";
		String[][] data =readExcel(excelPath,"Sheet1");
		String uName=data[0][0];
		WebElement userName=driver.findElement(By.id("username"));
		enterTextBox(userName,uName,"User Name");
		driver.findElement(By.id("password")).clear();
		logger.log(LogStatus.INFO,"Password Cleared");
		WebElement login=driver.findElement(By.id("Login"));
		String loginName="Login ";
		clickButton(login,loginName);		
		String actualError=driver.findElement(By.id("error")).getText();
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		String expectedError=data[2][0];
		assertEquals(actualError, expectedError,"Error"); 
	}
	
	@Test
	public void Testcase2() throws IOException {
		logger=reports.startTest("Testcase2 STARTED");
		String excelPath=System.getProperty("user.dir")+"/src/main/java/resources/SDFC.xls";
		String[][] data =readExcel(excelPath,"Sheet1");
		WebElement userName=driver.findElement(By.id("username"));
		enterTextBox(userName,data[0][0],"User Name");
		WebElement password=driver.findElement(By.id("password"));
		enterTextBox(password,data[1][0],"Password");
		WebElement login=driver.findElement(By.id("Login"));
		String loginName="Login ";
		clickButton(login,loginName);	
		WebElement home=driver.findElement(By.xpath("//td[@id='content_wrap:mruTable:0:mruName']/a"));
		wait.until(ExpectedConditions.visibilityOf(home));
		String expected=data[3][0];
		assertEquals(home.getText(),expected,"Home page not displayed");
		logger.log(LogStatus.PASS,"Home page displayed" );
	}
	
	@Test
	public void Testcase3() throws IOException {
		logger=reports.startTest("Testcase3 STARTED");
		String excelPath=System.getProperty("user.dir")+"/src/main/java/resources/SDFC.xls";
		String[][] data =readExcel(excelPath,"Sheet1");
		WebElement userName=driver.findElement(By.id("username"));
		enterTextBox(userName,data[0][0],"User Name");
		WebElement password=driver.findElement(By.id("password"));
		enterTextBox(password,data[1][0],"Password");
		WebElement rememberMe=driver.findElement(By.id("rememberUn"));
		wait.until(ExpectedConditions.visibilityOfAllElements(rememberMe));
		clickButton(rememberMe,"Remember Me Button");
		WebElement login=driver.findElement(By.id("Login"));
		String loginName="Login ";
		clickButton(login,loginName);
		WebElement home=driver.findElement(By.xpath("//td[@id='content_wrap:mruTable:0:mruName']/a"));
		wait.until(ExpectedConditions.visibilityOf(home));
		String expected=data[3][0];
		assertEquals(home.getText(),expected,"Home page not displayed");
		logger.log(LogStatus.PASS,"Home page displayed" );
		WebElement user=driver.findElement(By.id("userNavButton"));
		clickButton(user,expected);
		WebElement logout=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		clickButton(logout,"Logout");
		String currentWindow = driver.getWindowHandle();
        driver.switchTo().window(currentWindow);
		WebElement username =driver.findElement(By.id("idcard-identity"));
		wait.until(ExpectedConditions.visibilityOfAllElements(username));
		assertEquals(username.getText(),data[0][0],"Username Not displayed");
		logger.log(LogStatus.PASS,"Username Saved" );
	}
	
	@Test
	public void Testcase4a() throws IOException {
		logger=reports.startTest("Testcase4a` STARTED");
		String excelPath=System.getProperty("user.dir")+"/src/main/java/resources/SDFC.xls";
		String[][] data =readExcel(excelPath,"Sheet1");
		WebElement forgotPassword=driver.findElement(By.id("forgot_password_link"));
		clickButton(forgotPassword,"Forgot Password");
		WebElement uName=driver.findElement(By.id("un"));
		enterTextBox(uName,data[0][0],"User Name");
		clickButton(driver.findElement(By.id("continue")),"Continue Button");
		WebElement header=driver.findElement(By.id("header"));
		assertEquals(header.getText().trim(),data[4][0],"Check your email not displayed");
		logger.log(LogStatus.PASS,"Check your email  displayed" );
	}
	
	@Test
	public void Testcase4b() throws IOException {
		logger=reports.startTest("Testcase4b` STARTED");
		String excelPath=System.getProperty("user.dir")+"/src/main/java/resources/SDFC.xls";
		String[][] data =readExcel(excelPath,"Sheet1");
		WebElement userName=driver.findElement(By.id("username"));
		enterTextBox(userName,data[5][0],"User Name");
		WebElement password=driver.findElement(By.id("password"));
		enterTextBox(password,data[6][0],"Password");
		WebElement login=driver.findElement(By.id("Login"));
		String loginName="Login ";
		clickButton(login,loginName);
		String actualError=driver.findElement(By.id("error")).getText();
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		String expectedError=data[7][0];
		//assertEquals(actualError, expectedError,"Error"); 
		Assert.assertTrue(actualError.contains(expectedError));
		logger.log(LogStatus.PASS,"Error displayed");
	}
	@AfterMethod
	public void close() {
		driver.quit();
	}
	@AfterTest
	public void closeReport() {
		reports.endTest(logger);
		reports.flush();
	}
}
