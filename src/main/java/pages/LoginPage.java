package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader; // Import ConfigReader

public class LoginPage {
    private WebDriver driver;

    private By openAccountBtn = By.xpath("//*[@id=\"nav-link-accountList\"]");
    private By loginButton = By.xpath("//*[@id=\"nav-action-signin-button\"]");
    private By emailField = By.xpath("//input[@type='email']");
    private By continueButton = By.id("continue");
    private By passwordField = By.id("ap_password");
    private By signInButton = By.id("signInSubmit");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }
    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }
    public void accountClick() {
        driver.findElement(openAccountBtn).click();
    }

    public void login() {
    	accountClick();
        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");

        enterEmail(email);
        clickContinue();
        enterPassword(password);
        clickSignIn();
    }
}
