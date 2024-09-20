package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GiftPage extends BasePage{
	
	public GiftPage(WebDriver driver){
		super(driver);		
	}
	
	By gift_element_button = By.xpath("//li[@class='choosFrom__list--item'][5]");
	
	By birthday_gift_button = By.xpath("//h3[text()='Birthday Card']/ancestor::li");
	
	By choose_amount_label = By.xpath("//p[text()='Choose Amount']");
	
	@FindBy(id="amount")
	WebElement amount_input;
	
	@FindBy(xpath="//div[@class='quantity actives']/div/span[3]")
	WebElement quantity_increase_button;
	

	@FindBy(xpath="//input[@id='toggleSwitchGiftCard']")
	WebElement toggle_multiple_person_checkbox;
	
	
	
	@FindBy(xpath="//input[@name='senderName']")
	WebElement sender_name_input;
	
	@FindBy(xpath="//input[@name='senderMobileNo']")
	WebElement sender_mobile_no_input;
	
	@FindBy(xpath="//input[@name='senderEmailId']")
	WebElement sender_email_input;
	
	@FindBy(xpath="//button[@class='prime__btn font16 prime__btn__text']")
	WebElement submit_button;
	
	@FindBy(xpath="//div[@class='form__field form__field__error']/following-sibling::p")
	WebElement email_error_label;
	
	
	public void switchToGiftsSection() {
		logger.info("***** Waiting for the home page to load  *****");
		wait.until(ExpectedConditions.visibilityOfElementLocated(gift_element_button));
		
		logger.info("***** Home Page loaded successfully  *****");
		js.executeScript("arguments[0].click();",driver.findElement(gift_element_button));
		logger.info("***** Clicked Gifts section  *****");
		
		Set<String> idSet = driver.getWindowHandles();
		List<String> idList = new ArrayList<String>(idSet);
		
		for(String id: idList) {
			String title = driver.switchTo().window(id).getTitle();
			
			if(title.equals("Buy Gift Cards & Vouchers Online from MakeMyTrip")) {
				break;
			}
		}
		
		logger.info("***** Switched to new window *****");
		logger.info("***** Waiting for the gift page to load *****");
		wait.until(ExpectedConditions.visibilityOfElementLocated(birthday_gift_button));
		
		logger.info("***** Gift page loaded successfully  *****");
		js.executeScript("arguments[0].click();",driver.findElement(birthday_gift_button));
		logger.info("***** Clicked the birthday gift button  *****");
		
	}
	
	public String fillGiftSection(String amt, String senderName, String senderMobileNo,String senderEmailId,String quantity) {
		logger.info("***** Waiting for the Birthday gift page to load  *****");
		wait.until(ExpectedConditions.visibilityOfElementLocated(choose_amount_label));
		logger.info("***** Birthday gift page loaded successfully  *****");
		
		amount_input.clear();
		logger.info("***** Cleared the previous already existed value of the amount input  *****");
		amount_input.sendKeys(amt);
		logger.info("***** Set value to the amount input *****");

		
		for(int i = 0;i<Integer.parseInt(quantity)-1;i++) {
			js.executeScript("arguments[0].click();",quantity_increase_button);
		}
		
		logger.info("***** Clicked quantity increase button "+quantity+" times *****");
		js.executeScript("arguments[0].click();",toggle_multiple_person_checkbox);
		logger.info("***** Toggled multipe person checkbox  *****");
		
		
		for(int i = 1;i<=Integer.parseInt(quantity);i++) {			
			WebElement recipient_input = driver.findElement(By.xpath("//input[@name='Recipient "+i+"']"));
			recipient_input.sendKeys(RandomStringUtils.randomNumeric(10));
		}
		
		logger.info("***** Set recipient values in "+quantity+" input boxes  *****");
				
		
		sender_name_input.sendKeys(senderName);
		logger.info("***** Set name value  *****");
		
		sender_mobile_no_input.sendKeys(senderMobileNo);
		logger.info("***** Set phone number value  *****");
		
		sender_email_input.sendKeys(senderEmailId);
		logger.info("***** Set invalid email id  *****");
		
		submit_button.click();
		logger.info("***** Clicked the buy now button  *****");
		
		String errorMsg = email_error_label.getText();			
		return errorMsg;		
	}
	
	
	

}
