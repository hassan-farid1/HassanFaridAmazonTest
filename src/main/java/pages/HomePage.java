package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	private By allMenuButton = By.xpath("//*[@id='nav-hamburger-menu']");
	private By seeAllLinksBy = By.xpath("//a[@aria-label='See All Categories']");
	private By videoGamesCategory = By.xpath("//div[text()='Video Games']");
	private By allVideoGamesOption = By.xpath("//a[text()='All Video Games']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		js = (JavascriptExecutor) driver;
	}

	public void openAllMenu() {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(allMenuButton).click();
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public void selectAllCategories() {
		List<WebElement> seeAllLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(seeAllLinksBy));
		WebElement firstLink = wait.until(ExpectedConditions.visibilityOf(seeAllLinks.get(0)));
		firstLink.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	public void selectVideoGamesCategory() {
		js.executeScript(
				"let div = [...document.querySelectorAll('div')].find(el => el.innerText.trim() === 'Video Games');"
						+ "if (div) div.click();" + "setTimeout(() => {"
						+ "   let link = [...document.querySelectorAll('a')].find(el => el.innerText.trim() === 'All Video Games');"
						+ "   if (link) link.click();" + "}, 5000);");
	}

	public void navigateToAllVideoGames() {

		openAllMenu();
		selectAllCategories();
		selectVideoGamesCategory();
	}
}
