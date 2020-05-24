package Demo_TestNG1.Demo_TestNG1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestAnnotations {

	@BeforeSuite
	public void z1() {
		System.out.println("Before suite z1");
	}
	@BeforeSuite
	public void z2() {
		System.out.println("Before suite z2");
	}
	@BeforeTest
	public void s1() {
		System.out.println("Before Test");
	}
	@AfterTest
	public void s2() {
		System.out.println("After Test");
	
	}
	@Test(priority=-1)
	public void t1() {
		System.out.println("Test t1");
	}
	@Test
	public void at1() {
		System.out.println("Test at1");
	}
	
	
	@Test(priority=2,enabled=false)
	public void At1() {
		System.out.println("Test At1");
	}
	@BeforeMethod
	public void m1() {
		System.out.println("Before Method m1");
	}
	@AfterMethod
	public void m2() {
		System.out.println("After Method m2");
	}
	@BeforeClass
	public void c1() {
		System.out.println("Before class c1");
	}
	@AfterClass
	public void c2() {
		System.out.println("After class c2");
	}
}
