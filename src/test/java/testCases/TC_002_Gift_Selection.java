package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.GiftPage;
import utilities.dataProvider;

public class TC_002_Gift_Selection {
WebDriver driver;
Logger logger;
	
	@BeforeClass
	void setUp() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@AfterClass
	void tearDown() {
		//log the message like closing the browser
		driver.quit();
	}
	
	@Test(dataProvider="giftDetails",dataProviderClass=dataProvider.class)
	void verifyGiftErrorMessage(String amt, String senderName, String senderMobileNo,String senderEmailId,String quantity){
		logger.info("***** Test Case 002 started execution  *****");
		GiftPage g = new GiftPage(driver);
		g.switchToGiftsSection();	
		
		//place this value in the data file (like in json) 
		String expected_message ="Please enter a valid Email id."; 
		String actual_message =g.fillGiftSection(amt,senderName,senderMobileNo,senderEmailId,quantity); 
		System.out.println(actual_message);
		Assert.assertEquals(actual_message,expected_message);
		logger.info("***** Test case 002 completed execution  *****");
	}

}
