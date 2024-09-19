package pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;	
	JavascriptExecutor js;
	WebDriverWait wait;
	Logger logger;
	
	
	BasePage(WebDriver driver){
		this.driver = driver;
		logger=LogManager.getLogger(this.getClass());
		js = (JavascriptExecutor)this.driver;
		wait = new WebDriverWait(this.driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}

}
