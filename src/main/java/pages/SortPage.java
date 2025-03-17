package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SortPage {
    private WebDriver driver;

    private By dropdown = By.id("s-result-sort-select");

    public SortPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sortByPriceHighToLow() {
		Select select = new Select(driver.findElement(dropdown));
		select.selectByValue("price-desc-rank");
    }
}
