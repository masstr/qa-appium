package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private By emailField_Locator = By.id("com.mobgen.interview.mobgeninterviewtest:id/email");
    private By passwordField_Locator = By.id("com.mobgen.interview.mobgeninterviewtest:id/password");
    private By signinButton_Locator = By.id("com.mobgen.interview.mobgeninterviewtest:id/email_sign_in_button");

    public WebElement emailField;
    public WebElement passwordField;
    public WebElement siginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage waitForLoad() {
        waitForVisibilityOf(emailField_Locator);
        waitForVisibilityOf(passwordField_Locator);
        waitForVisibilityOf(signinButton_Locator);
        emailField = driver.findElement(emailField_Locator);
        passwordField = driver.findElement(passwordField_Locator);
        siginButton = driver.findElement(signinButton_Locator);
        return this;
    }

    public void enterEmail(String email_text) {
        emailField.clear();
        emailField.sendKeys(email_text);
    }

    public void enterPassword(String password_text) {
        passwordField.clear();
        passwordField.sendKeys(password_text);
    }

    public void waitForSignInButtonAvailable() {
        waitForClickabilityOf(signinButton_Locator);
    }

    public void loginWithEmailAndPassword(String email_text, String password_text) {
        enterEmail(email_text);
        enterPassword(password_text);
        waitForSignInButtonAvailable();
        siginButton.click();
    }

    public boolean isStillLoginPage() {
        return (driver.findElements(emailField_Locator).size() > 0) && (driver.findElements(passwordField_Locator).size() > 0) && (driver.findElements(signinButton_Locator).size() > 0);
    }
}