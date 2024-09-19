package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class HomePage extends BasePage{
	
	
	public HomePage(WebDriver driver){
		super(driver);		
	}

	By login_dialog = By.xpath("//span[@class='commonModal__close']");	
	
	@FindBy(xpath="//*[@id=\"SW\"]//nav/ul/li[7]//a")
	WebElement cabs_button;
	
	@FindBy(xpath="//*[@id=\"SW\"]//nav//li[@class='menu_Hotels']")
	WebElement hotels_button;
	
	By search_button = By.xpath("//*[@id=\"top-banner\"]/div[2]//a[text()='Search']");	
	
	@FindBy(id="fromCity")
	WebElement fake_from_input_box;
	
	By main_from_input_box = By.xpath("//div[@id='react-autowhatever-1']/preceding-sibling::input");
	
	
	By recommended_cities_label = By.xpath("//*[@id=\"react-autowhatever-1\"]/div/div/p"); 
	By first_recommended_from_city = By.id("react-autowhatever-1-section-0-item-1");
		
	@FindBy(xpath="//div[@id='react-autowhatever-1']/preceding-sibling::input")
	WebElement main_to_input_box;
	
	By first_recommended_to_city = By.id("react-autowhatever-1-section-0-item-0");
	
	
	@FindBy(xpath="//*[@id='top-banner']/div[2]/div/div/div[2]/div[1]/div[3]/label")
	WebElement sample_presented_date_label;
	
	
	
	
	
	@FindBy(xpath="//*[@id=\"top-banner\"]/div[2]//label[@for='pickupTime']")
	WebElement sample_pick_up_time_button;
	
	
	
	@FindBy(xpath="//span[@class='applyBtnText']")
	WebElement apply_pickUp_time_button;

	@FindBy(xpath="//a[@class='chMmtLogo']")
	WebElement home_page_relocator;	
	
	By for_guest_label = By.xpath("//label[@for='guest']");
	
	@FindBy(xpath="//span[@data-testid='adult_count']/parent::div")
	WebElement adult_dropdown;
	
	
	By adult_count_options = By.xpath("//li[@data-cy='GuestSelect$$_323']");
	
	
	public void closeDialog() {
		try {
			//waiting for the dialog box to appear
			logger.info("***** Waiting for the 'login' pop up to load  *****");
			wait.until(ExpectedConditions.visibilityOfElementLocated(login_dialog));
			logger.info("***** Login popup appeared successfully  *****");
			//closing the dialog button after it appears			
			driver.findElement(login_dialog).click();
			logger.info("***** Closed the login popup  *****");
		}catch(Exception e) {
			return;
		}
		
	}
	
	
	public void clickCabsSection() {
		
		//switching to cabs section
		cabs_button.click();
		logger.info("***** Switched to Cabs Section  *****");		
		//waiting until the cabs section is displayed 
		logger.info("***** Waiting for the Cabs section to load  *****");
		wait.until(ExpectedConditions.visibilityOfElementLocated(search_button));
		logger.info("***** Cabs Section loaded successfully  *****");
	}
	
	public void clickHotelsSection() {
		logger.info("***** Waiting for the Home Page to load  *****");
		wait.until(ExpectedConditions.visibilityOfElementLocated(search_button));		
		logger.info("***** Home Page Loaded Successfully  *****");
		js.executeScript("arguments[0].click();",hotels_button);
		logger.info("***** Switched to Hotels Section  *****");
		
	}
	
	public String getHotelData() {
		logger.info("***** Waiting for the hotels section to load  *****");
		wait.until(ExpectedConditions.visibilityOfElementLocated(for_guest_label));
		
		logger.info("***** Hotels section loaded successfully  *****");
		driver.findElement(for_guest_label).click();
		logger.info("***** Clicked Rooms & Guests Section  *****");
		
		adult_dropdown.click();
		logger.info("***** Clicked Adults dropdown  *****");
		List<WebElement> noOfAdults = driver.findElements(adult_count_options);
		return noOfAdults.size()+"";		
	}
	
	
	
	
	
	//now we can hardCode and in future we can change to dynamic data concept
	public void fillCabData(String from, String to, String date, String pickUpHr, String pickUpMin, String pickUpMeridian){
		
		//the logic is there are two input boxes present for getting one input, so we
		//first we need to click the fake input box and second click the next input box and then,
		//enter values in the second input box
		fake_from_input_box.click();
		logger.info("***** Clicked the hidden 'from' input box  *****");
		
		//waiting until the main input box(second input box)appears 
		wait.until(ExpectedConditions.visibilityOfElementLocated(main_from_input_box));
		
		//clicking the main input box and sending the values 
		driver.findElement(main_from_input_box).click();
		logger.info("***** Clicked the main 'from' input box  *****");
		driver.findElement(main_from_input_box).sendKeys(from);
		logger.info("***** Sent values to the main 'from' input box  *****");
		
		//waiting for the recommendation to complete and clicking the first recommended city
		wait.until(ExpectedConditions.visibilityOfElementLocated(first_recommended_from_city));
		logger.info("***** Waiting for the 'from' cities to be recommended  *****");
		driver.findElement(first_recommended_from_city).click();
		logger.info("***** Clicked the first recommended 'from' city  *****");
		
		
		//in "from" and "to" input boxes the logic are same but,
		//in "from" input box we need to click both the fake and main input boxes and send input to the main
		//input box, but 
		//in "to" input box automatically both the fake and real input boxes will be clicked and so just sending 
		//the values		
		main_to_input_box.sendKeys(to);
		logger.info("***** Sent values to the main 'to' input box  *****");
		
		//waiting until the "to" cities are recommended
		wait.until(ExpectedConditions.textToBe(recommended_cities_label,"SUGGESTIONS"));
		logger.info("***** Waiting for the 'to' cities to be recommended  *****");
		driver.findElement(first_recommended_to_city).click();
		logger.info("***** Clicked the first 'to' recommended city  *****");
		
		//clicking the date section
		sample_presented_date_label.click();
		logger.info("***** Clicked the dates section *****");
		
		By departure_date_value_button = By.xpath("//div[@aria-label='"+date+"']");
		//wating for the custom date section to be appeared
		wait.until(ExpectedConditions.visibilityOfElementLocated(departure_date_value_button));
		driver.findElement(departure_date_value_button).click();
		logger.info("***** Clicked the required date *****");
		
		//clicking the pickup time button
		sample_pick_up_time_button.click();
		logger.info("***** Clicked the pickup time section *****");
		
		WebElement hour_value_button = driver.findElement(By.xpath("//span[contains(@class,'hrSlotItemChild') and text()='"+pickUpHr+"']/parent::li"));
		WebElement min_value_button = driver.findElement(By.xpath("//span[@class='minSlotItemChild' and text()='"+pickUpMin+"']/parent::li"));
		WebElement meridian_slot_value = driver.findElement(By.xpath("//span[contains(@class,'meridianSlotItemChild') and text()='"+pickUpMeridian+"']/parent::li"));
		
		
		//clicking the hr value from dropdown
		js.executeScript("arguments[0].click();",hour_value_button );
		logger.info("***** Hour value set *****");
		

		//clicking the min value from dropdown
		js.executeScript("arguments[0].click();", min_value_button);
		logger.info("***** Minutes value set *****");
		
		//clicking the meredian value from dropdown
		js.executeScript("arguments[0].click();",meridian_slot_value);
		logger.info("***** Meredian value set *****");
		//clicking the apply button
		js.executeScript("arguments[0].click();",apply_pickUp_time_button);
		logger.info("***** Clicked the apply button *****");
		//clicking the search button		
		driver.findElement(search_button).click();	
		logger.info("***** After entering all the data search button is clicked *****");
	}
	
	public void relocateToHomePage() {		
		
		js.executeScript("arguments[0].click();",home_page_relocator);
		logger.info("***** Relocated to home page *****");
	}
}
