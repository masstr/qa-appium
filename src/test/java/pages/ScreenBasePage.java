package pages;

import base_classes.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by kkarpov on 26.04.17.
 */
public class ScreenBasePage extends BasePage {

    private int current_screen = 1;
    private WebElement oneButton = null;
    private WebElement twoButton = null;
    public WebElement addButton = null;
    private By itemName_Locator = By.id("com.mobgen.interview.mobgeninterviewtest:id/name_textview");
    private By itemJob_Locator = By.id("com.mobgen.interview.mobgeninterviewtest:id/job_textview");
    private By controlButton_Locator = By.id("com.mobgen.interview.mobgeninterviewtest:id/imageHolder");
    private By textView_Locator = By.className("android.widget.TextView");
    private By addButton_Locator = By.id("com.mobgen.interview.mobgeninterviewtest:id/floating_add_button");

    public ScreenBasePage(WebDriver driver) {
        super(driver);
    }

    public void waitForLoad() {
        waitForVisibilityOf(addButton_Locator);
        addButton = driver.findElement(addButton_Locator);
        List<WebElement> elements = driver.findElements(textView_Locator);
        for (WebElement web_el: elements) {
            if (web_el.getText().equals("ONE")) {
                oneButton = web_el;
            }
            if (web_el.getText().equals("TWO")) {
                twoButton = web_el;
            }
            if (oneButton != null && twoButton != null) {
                break;
            }
        }
    }

    public boolean isSameScreen() {
        if (current_screen == 1) {
            return isScreenOne();
        } else {
            return isScreenTwo();
        }
    }

    public void goToScreenOne() {
        oneButton.click();
        current_screen = 1;
    }

    public void goToScreenTwo() {
        twoButton.click();
        current_screen = 2;
    }

    public boolean isScreenOne() {
        return oneButton.isSelected();
    }

    public boolean isScreenTwo() {
        return twoButton.isSelected();
    }

    public void addNewItem(String main_text, String desc_text) {
        addButton.click();
        AddNewItemPage addNewItemPage = new AddNewItemPage(driver);
        addNewItemPage.waitForLoad();
        addNewItemPage.enterMainAndDesc(main_text, desc_text);
        addNewItemPage.acceptButton.click();
        Assert.assertTrue(isSameScreen());
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<Item>();
        List<WebElement> names = driver.findElements(itemName_Locator);
        List<WebElement> jobs = driver.findElements(itemJob_Locator);
        List<WebElement> controlButtons = driver.findElements(controlButton_Locator);
        Assert.assertEquals(names.size(), jobs.size());
        Assert.assertEquals(names.size(), controlButtons.size());
        for (int i = 0; i < names.size(); i++) {
            items.add(
                    new Item(
                            names.get(i).getText(),
                            jobs.get(i).getText(),
                            controlButtons.get(i)
                    )
            );
        }
        return items;
    }

    public Item findItemWithMainAndDesc(String main_text, String desc_text) {
        List<Item> items = getAllItems();
        for (Item item: items) {
            if (item.mainText.equals(main_text) && item.descText.equals(desc_text)) {
                return item;
            }
        }
        return null;
    }
}
