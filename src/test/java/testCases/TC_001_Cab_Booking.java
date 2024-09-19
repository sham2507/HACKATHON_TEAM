package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.CabsPage;
import pageObjects.HomePage;
import utilities.dataProvider;

public class TC_001_Cab_Booking {
	
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
	
	@Test(dataProvider="bookingDetails",dataProviderClass=dataProvider.class)
	void verifyMinimumPrice(String from, String to, String date, String pickUpHr, String pickUpMin, String pickUpMeridian) {
		logger.info("***** Test Case 01 Started *****");
		HomePage h = new HomePage(driver);
		
		h.closeDialog();
		h.clickCabsSection();
		h.fillCabData(from, to, date, pickUpHr, pickUpMin, pickUpMeridian);
		
		CabsPage c = new CabsPage(driver);
		
		
		//optimize it in the way like, get the list of all the prices and sort it and do it in the way
		String expected_result = "₹ 5,824";
		boolean verificationResult = c.getPriceDetails();
		//h.relocateToHomePage();
		Assert.assertTrue(verificationResult);
		logger.info("***** Test Case 01 Completed Execution *****");
		
	}
	
	
}
