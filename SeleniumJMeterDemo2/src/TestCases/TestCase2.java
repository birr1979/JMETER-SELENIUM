package TestCases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase2 {
	WebDriver dr=null;
	
	@Before
	public void BeforeTest() {
		System.out.println("Before test starting execution");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\birr_\\Desktop\\JMETERFILES\\chromedriver.exe");
		dr= new ChromeDriver();
		dr.manage().window().maximize();
	}
	@After
	public void AfterTest() {
		System.out.println("Test execution Finished closing Browser");
		dr.close();	
	}
	
	@Test
	public void Test1() {
		System.out.println("Test execution 1");
		dr.get("https://www.afrocouple.com/");	
		}
	
	@Test
	public void Test2() {
		System.out.println("Test execution 2");
		dr.get("https://www.usda.gov/");	
	}
	
	@Test
	public void Test3() {
		
		System.out.println("Test 3 execution");
	}
	

}
