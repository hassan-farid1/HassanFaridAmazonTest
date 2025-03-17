package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterPage {
	private WebDriver driver;

	private By freeShippingFilter = By.xpath("//span[text()='Free Shipping']/preceding-sibling::div[1]");
	private By newConditionFilter = By.xpath("//span[text()='New']");

	private WebDriverWait wait;

	public FilterPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void applyFreeShippingFilter() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(freeShippingFilter)).click();
	}

	public void applyNewConditionFilter() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(newConditionFilter)).click();
	}

	public void applyFilters() {
		applyFreeShippingFilter();
		applyNewConditionFilter();
	}
}
