package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    By emailField_Locator = By.id("com.mobgen.interview.mobgeninterviewtest:id/email");
    By passwordField_Locator = By.id("com.mobgen.interview.mobgeninterviewtest:id/password");
    By signinButton_Locator = By.id("com.mobgen.interview.mobgeninterviewtest:id/email_sign_in_button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage waitForLoad() {
        waitForVisibilityOf(emailField_Locator);
        waitForVisibilityOf(passwordField_Locator);
        waitForVisibilityOf(signinButton_Locator);
        return new LoginPage(driver);
    }

    public void enterEmail(String email_text) {
        driver.findElement(emailField_Locator).sendKeys(email_text);
    }

    public void enterPassword(String password_text) {
        driver.findElement(passwordField_Locator).sendKeys(password_text);
    }

    public void waitForSignInButtonAvailable() {
        waitForClickabilityOf(signinButton_Locator);
    }

    public boolean isSignInButtonClickable() {
        return driver.findElement(signinButton_Locator).isEnabled();
    }

    public void clearEmail() {
        driver.findElement(emailField_Locator).clear();
    }

    public void clearPassword() {
        driver.findElement(passwordField_Locator).clear();
    }

    public void clickSigInButton() {
        driver.findElement(signinButton_Locator).click();
    }

    public void loginWithEmailAndPassword(String email_text, String password_text) {
        enterEmail(email_text);
        enterPassword(password_text);
        waitForSignInButtonAvailable();
        clickSigInButton();
    }

    public boolean isStillLoginPage() {
        return (driver.findElements(emailField_Locator).size() > 0) && (driver.findElements(passwordField_Locator).size() > 0) && (driver.findElements(signinButton_Locator).size() > 0);
    }
}