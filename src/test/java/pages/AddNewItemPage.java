package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by kkarpov on 27.04.17.
 */
public class AddNewItemPage extends BasePage {
    private By cancelButton_Locator = By.id("android:id/button2");
    private By acceptButton_Locator = By.id("android:id/button1");
    private By textField_Locator = By.className("android.widget.EditText");

    private WebElement mainEdit = null;
    private WebElement descEdit = null;

    public WebElement cancelButton = null;
    public WebElement acceptButton = null;

    public AddNewItemPage(WebDriver driver) {
        super(driver);
    }

    public void waitForLoad() {
        waitForVisibilityOf(cancelButton_Locator);
        waitForVisibilityOf(acceptButton_Locator);
        cancelButton = driver.findElement(cancelButton_Locator);
        acceptButton = driver.findElement(acceptButton_Locator);
        findMainAndDescFields();
    }

    private void findMainAndDescFields() {
        List elements = driver.findElements(textField_Locator);
        Assert.assertEquals(2, elements.size());
        mainEdit = (WebElement) elements.get(0);
        descEdit = (WebElement) elements.get(1);
    }

    public void enterMainAndDesc(String main_text, String desc_text) {
        mainEdit.clear();
        mainEdit.sendKeys(main_text);
        descEdit.clear();
        descEdit.sendKeys(desc_text);
    }
}
