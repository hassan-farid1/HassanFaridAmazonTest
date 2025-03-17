package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private By productPriceBy = By.xpath(
			".//button[contains(@aria-label, 'Add to cart')]/ancestor::div[contains(@class, 's-result-item')]//span[@class='a-price-whole']");
	private By addToCartButtonBy = By.xpath(
			".//span[@class='a-price-whole']/ancestor::div[contains(@class, 's-result-item')]//button[starts-with(@id, 'a-autoid') and contains(@aria-label, 'Add to cart')]");
	private By nextPageButton = By.xpath("//a[contains(@class, 's-pagination-next')]");

	public CartPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	public void addProductsToCart() {
		List<WebElement> priceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productPriceBy));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		List<WebElement> addToCartButton = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(addToCartButtonBy));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println(priceElements.size());

		for (int i = 0; i < priceElements.size(); i++) {
			try {
				String priceText = priceElements.get(i).getText().replace(",", "").trim();
				if (!priceText.isEmpty()) {
					double price = Double.parseDouble(priceText);

					if (price < 15000) {
						if (addToCartButton.get(i).isDisplayed()) {
							addToCartButton.get(i).click();
							System.out.println("Added to cart: Item with price " + price);
							Thread.sleep(2000);
						} else {
							continue;
						}

					}
				}
			} catch (Exception e) {
				System.out.println("Skipping item due to error: " + e.getMessage());
			}
		}

	}

	public void nextPage() {
		driver.findElement(nextPageButton).click();
	}
}
