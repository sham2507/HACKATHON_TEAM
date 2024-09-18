package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	
	@FindBy(xpath="//input[@name='Recipient 1']")
	WebElement recipient_1_input;
	
	@FindBy(xpath="//input[@name='Recipient 2']")
	WebElement recipient_2_input;
	
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(gift_element_button));
		
		js.executeScript("arguments[0].click();",driver.findElement(gift_element_button));
		
		Set<String> idSet = driver.getWindowHandles();
		List<String> idList = new ArrayList<String>(idSet);
		
		for(String id: idList) {
			String title = driver.switchTo().window(id).getTitle();
			
			if(title.equals("Buy Gift Cards & Vouchers Online from MakeMyTrip")) {
				break;
			}
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(birthday_gift_button));
		
		js.executeScript("arguments[0].click();",driver.findElement(birthday_gift_button));
	}
	
	public String fillGiftSection(String amt, String senderName, String senderMobileNo,String senderEmailId) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(choose_amount_label));
		
		amount_input.clear();
		amount_input.sendKeys(amt);
		
		js.executeScript("arguments[0].click();",quantity_increase_button);
		js.executeScript("arguments[0].click();",toggle_multiple_person_checkbox);
		
		recipient_1_input.sendKeys("9988554433");
		recipient_2_input.sendKeys("5544334455");
				
		
		sender_name_input.sendKeys(senderName);
		
		sender_mobile_no_input.sendKeys(senderMobileNo);
		
		sender_email_input.sendKeys(senderEmailId);
		
		submit_button.click();
		
		String errorMsg = email_error_label.getText();			
		return errorMsg;
		
	}
	
	
	

}
