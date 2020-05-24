package Demo_TestNG1.Demo_TestNG1;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Annotations {

	@BeforeSuite
	//to launch a browser
	public void beforeSuite() {
		System.out.println("Before Suite");
	}
	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite");
	}
	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test");
	}
	@AfterTest
	public void afterTest() {
		System.out.println("AfterTest");
	}
	@Test	
	public void test1(){
			System.out.println("Test Executed");
		}

	}



 