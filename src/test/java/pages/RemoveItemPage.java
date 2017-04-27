package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import test.tmp.Base;

/**
 * Created by kkarpov on 27.04.17.
 */
public class RemoveItemPage extends BasePage {
    private By title_Locator = By.id("com.mobgen.interview.mobgeninterviewtest:id/alertTitle");
    private By cancelButton_Locator = By.id("android:id/button2");
    private By acceptButton_Locator = By.id("android:id/button1");

    public WebElement cancelButton = null;
    public WebElement acceptButton = null;
    private WebElement title = null;

    public RemoveItemPage(WebDriver driver) {
        super(driver);
    }

    public void waitForLoad() {
        waitForVisibilityOf(title_Locator);
        title = driver.findElement(title_Locator);
        Assert.assertEquals("Delete item", title.getText());
        waitForVisibilityOf(cancelButton_Locator);
        waitForVisibilityOf(acceptButton_Locator);
        cancelButton = driver.findElement(cancelButton_Locator);
        acceptButton = driver.findElement(acceptButton_Locator);
    }
}
