package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckoutPage {
	private WebDriver driver;

	private By cartItems = By.xpath("//div[contains(@class,'sc-list-item-content')]");
	private By subtotalAmount = By.xpath("//span[@id='sc-subtotal-amount-activecart']");
	private By proceedToCheckoutButton = By.name("proceedToRetailCheckout");
	private By addressOption = By.xpath("//input[@name='shipToThisAddress']");
	private By cashOnDeliveryOption = By.xpath("//input[@value='Cash on Delivery']");
	private By placeOrderButton = By.xpath("//input[@name='placeYourOrder1']");

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyCartItems() {
		List<WebElement> items = driver.findElements(cartItems);
		return !items.isEmpty();
	}

	public void proceedToCheckout() {
		driver.findElement(proceedToCheckoutButton).click();
	}

	public void selectAddress() {
		driver.findElement(addressOption).click();
	}

	public void chooseCashOnDelivery() {
		driver.findElement(cashOnDeliveryOption).click();
	}

	public String getTotalAmount() {
		return driver.findElement(subtotalAmount).getText();
	}

	public void completeCheckout() {
		proceedToCheckout();
		selectAddress();
		chooseCashOnDelivery();
		System.out.println("Total Amount: " + getTotalAmount());
	}
}
