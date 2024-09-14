package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePage;

public class TC_003_Hotel_Booking {
	WebDriver driver;
	@BeforeClass
	void setUp() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
	}
	
	@AfterClass
	void tearDown() {
		//log the message like closing the browser
		driver.quit();
	}
	
	@Test
	void getAdultsCount() {
		
		HomePage h = new HomePage(driver);
		h.closeDialog();
		h.clickHotelsSection();
		String count = h.getHotelData();
		
		System.out.println(count);
		
		Assert.assertTrue(true);		
	}

}
