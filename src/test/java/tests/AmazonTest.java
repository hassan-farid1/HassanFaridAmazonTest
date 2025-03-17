package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CartPage;
import pages.FilterPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SortPage;
import utils.ConfigReader;

public class AmazonTest {
	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private FilterPage filterPage;
	private SortPage sortPage;
	private CartPage cartPage;


	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.amazon.eg/");
		driver.manage().window().maximize();
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		filterPage = new FilterPage(driver);
		sortPage = new SortPage(driver);
		cartPage = new CartPage(driver);
	}

	@Test(priority = 1)
	public void testLogin() {
		driver.get(ConfigReader.getProperty("baseURL"));
		loginPage.login();
		Assert.assertTrue(driver.getTitle().contains("Amazon"));
	}

	@Test(priority = 2)
	public void showVideoGames() {
		homePage.navigateToAllVideoGames();
	}

	@Test(priority = 3)
	public void testFiltersAndSorting() {
		filterPage.applyFilters();
		sortPage.sortByPriceHighToLow();
	}

	@Test(priority = 4)
	public void testAddToCart() {
		for (int i = 0; i < 100; i++) {
			cartPage.addProductsToCart();
			cartPage.nextPage();
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
