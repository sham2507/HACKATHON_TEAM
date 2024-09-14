package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.GiftPage;

public class TC_002_Gift_Selection {
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
	void verifyGiftErrorMessage() {
		GiftPage g = new GiftPage(driver);
		g.switchToGiftsSection();	
		
		//place this value in the data file (like in json) 
		String expected_message ="Please enter a valid Email id."; 
		String actual_message =g.fillGiftSection(); 
		System.out.println(actual_message);
		
		Assert.assertEquals(actual_message,expected_message);
	}

}
