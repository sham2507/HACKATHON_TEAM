package testCases;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.CabsPage;
import pageObjects.HomePage;

public class TC_001_Cab_Booking {
	
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
	void verifyMinimumPrice() {
		HomePage h = new HomePage(driver);
		
		h.closeDialog();
		h.clickCabsSection();
		h.fillCabData();
		
		CabsPage c = new CabsPage(driver);
		
		
		//optimize it in the way like, get the list of all the prices and sort it and do it in the way
		String expected_result = "₹ 5,824";
		boolean verificationResult = c.getPriceDetails();
		
		Assert.assertTrue(verificationResult);
		
	}
	
	
}
