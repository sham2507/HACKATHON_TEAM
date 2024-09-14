package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CabsPage extends BasePage{
	public CabsPage(WebDriver driver){
		super(driver);
	}
	
	By filter_name_label = By.xpath("//p[text()='Cab Type']");
	
	@FindBy(xpath="//p[text()='Cab Type']/following-sibling::div/span/label[text()='SUV']/preceding-sibling::input")
	WebElement specific_cab_type_checkbox;
	
	By vehicle_image = By.xpath("//*[@class='vehicleImg']");
	
	By list_of_cabs = By.id("List");
	
	By price_label_locator =By.xpath("//div[contains(@class,'priceDetailsPadding')]/div[2]/div/p[1]");
	
	
	public boolean getPriceDetails() {
		
		//waiting until the filter option is available		
		wait.until(ExpectedConditions.visibilityOfElementLocated(filter_name_label));
		
		//applying an specific filter option
		js.executeScript("arguments[0].click();",specific_cab_type_checkbox);
		
		//waiting until the filter option is applied and some result is returned
		wait.until(ExpectedConditions.visibilityOfElementLocated(vehicle_image));
		
		
		//waiting until the new cab's price is loaded
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(list_of_cabs)));
		
		List<Integer> prices_list = new ArrayList<Integer>();
		
		for(WebElement price_label : driver.findElements(price_label_locator)) {
			prices_list.add(Integer.parseInt(price_label.getText().replace("₹","").replace(",","").trim()));
		}
		//getting the price of the first cab
		Collections.sort(prices_list);
		
		int actual_result = Integer.parseInt(driver.findElement(price_label_locator).getText().replace("₹","").replace(",","").trim());
		
		
		return actual_result ==prices_list.get(0);
	}

}
