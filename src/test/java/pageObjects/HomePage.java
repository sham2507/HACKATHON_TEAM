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
	
	
	By departure_date_value_button = By.xpath("//div[@aria-label='Sat Sep 28 2024']");
	
	
	@FindBy(xpath="//*[@id=\"top-banner\"]/div[2]//label[@for='pickupTime']")
	WebElement sample_pick_up_time_button;
	
	@FindBy(xpath="//span[contains(@class,'hrSlotItemChild') and text()='06  Hr']/parent::li")
	WebElement hour_value_button;
	
	@FindBy(xpath="//span[@class='minSlotItemChild' and text()='30  min']/parent::li")
	WebElement min_value_button;
	
	@FindBy(xpath="//span[contains(@class,'meridianSlotItemChild') and text()='AM']/parent::li")
	WebElement meridian_slot_value;
	
	@FindBy(xpath="//span[@class='applyBtnText']")
	WebElement apply_pickUp_time_button;

	@FindBy(xpath="//a[@class='chMmtLogo']")
	WebElement home_page_relocator;	
	
	By for_guest_label = By.xpath("//label[@for='guest']");
	
	@FindBy(xpath="//span[@data-testid='adult_count']/parent::div")
	WebElement adult_dropdown;
	
	
	By adult_count_options = By.xpath("//li[@data-cy='GuestSelect$$_323']");
	
	
	public void closeDialog() {			
		//waiting for the dialog box to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(login_dialog));
		
		//closing the dialog button after it appears
		driver.findElement(login_dialog).click();
	}
	
	
	public void clickCabsSection() {
		
		//switching to cabs section
		cabs_button.click();
		
		//waiting until the cabs section is displayed 
		wait.until(ExpectedConditions.visibilityOfElementLocated(search_button));
	}
	
	public void clickHotelsSection() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(search_button));
		js.executeScript("arguments[0].click();",hotels_button);
		
	}
	
	public String getHotelData() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(for_guest_label));
		driver.findElement(for_guest_label).click();
		
		adult_dropdown.click();
		
		List<WebElement> noOfAdults = driver.findElements(adult_count_options);
		return noOfAdults.size()+"";
		
	}
	
	
	
	
	
	//now we can hardCode and in future we can change to dynamic data concept
	public void fillCabData(){
		
		//the logic is there are two input boxes present for getting one input, so we
		//first we need to click the fake input box and second click the next input box and then,
		//enter values in the second input box
		fake_from_input_box.click();
		
		//waiting until the main input box(second input box)appears 
		wait.until(ExpectedConditions.visibilityOfElementLocated(main_from_input_box));
		
		//clicking the main input box and sending the values 
		driver.findElement(main_from_input_box).click();
		driver.findElement(main_from_input_box).sendKeys("Delhi");
		
		//waiting for the recommendation to complete and clicking the first recommended city
		wait.until(ExpectedConditions.visibilityOfElementLocated(first_recommended_from_city));
		driver.findElement(first_recommended_from_city).click();
		
		//in "from" and "to" input boxes the logic are same but,
		//in "from" input box we need to click both the fake and main input boxes and send input to the main
		//input box, but 
		//in "to" input box automatically both the fake and real input boxes will be clicked and so just sending 
		//the values		
		main_to_input_box.sendKeys("manali");
		
		//waiting until the "to" cities are recommended
		wait.until(ExpectedConditions.textToBe(recommended_cities_label,"SUGGESTIONS"));
		
		driver.findElement(first_recommended_to_city).click();
		
		//clicking the date section
		sample_presented_date_label.click();
		
		//wating for the custom date section to be appeared
		wait.until(ExpectedConditions.visibilityOfElementLocated(departure_date_value_button));
		driver.findElement(departure_date_value_button).click();
		
		//clicking the pickup time button
		sample_pick_up_time_button.click();
		
		//clicking the hr value from dropdown
		js.executeScript("arguments[0].click();",hour_value_button );
		

		//clicking the min value from dropdown
		js.executeScript("arguments[0].click();", min_value_button);
		
		//clicking the meredian value from dropdown
		js.executeScript("arguments[0].click();",meridian_slot_value);
		
		//clicking the apply button
		js.executeScript("arguments[0].click();",apply_pickUp_time_button);
		
		//clicking the search button		
		driver.findElement(search_button).click();		
	}
	
	public void relocateToHomePage() {		
		js.executeScript("arguments[0].click();",home_page_relocator);		
	}
	
	
//	public static void main(String[] args) throws InterruptedException {
//		WebDriver driver = new ChromeDriver();
//		
//		driver.manage().window().maximize();		
//		driver.get("https://www.makemytrip.com/");
//		
//		
//		HomePage h = new HomePage(driver);
//		
//		h.closeDialog();
//		h.clickCabsSection();
//		h.fillCabData();
//		
//		CabsPage c = new CabsPage(driver);
//		c.getPriceDetails();
//		
//		h.relocateToHomePage();
//		
//		GiftPage g = new GiftPage(driver);
//		g.switchToGiftsSection();
//		
//		g.fillGiftSection();
//		
//		h.relocateToHomePage();
//		
//		h.clickHotelsSection();
//		h.getHotelData();
//	}
}
