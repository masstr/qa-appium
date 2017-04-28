package base_classes;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddNewItemPage;
import pages.LoginPage;
import pages.ScreenBasePage;

/**
 * Created by kkarpov on 27.04.17.
 */
public class ScreenPageBaseTest extends AndroidSetup {
    public String test_main = "TestText1";
    public String test_desc = "TestText2";
    protected ScreenBasePage screenPage;

    @BeforeMethod
    public void setUp() throws Exception {
        prepareAndroidForAppium();
        new LoginPage(driver).waitForLoad().loginWithEmailAndPassword("some@test.ru", "12345");
        screenPage = new ScreenBasePage(driver);
        screenPage.waitForLoad();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
        driver = null;
        screenPage = null;
    }

    @Test
    public void addNewItemTest() throws InterruptedException {
        int items_size_before = screenPage.getAllItems().size();
        screenPage.addNewItem(test_main, test_desc);
        Item new_item = screenPage.findItemWithMainAndDesc(test_main, test_desc);
        Assert.assertNotNull(new_item);
        int items_size_after_adding = screenPage.getAllItems().size();
        Assert.assertEquals(items_size_before + 1, items_size_after_adding);
    }

    @Test
    public void falseAddNewItemTest() throws InterruptedException {
        int items_size_before = screenPage.getAllItems().size();
        screenPage.addButton.click();
        AddNewItemPage addNewItemPage = new AddNewItemPage(driver);
        addNewItemPage.waitForLoad();
        addNewItemPage.enterMainAndDesc(test_main, test_desc);
        addNewItemPage.cancelButton.click();
        Assert.assertTrue(screenPage.isSameScreen());
        int items_size_after = screenPage.getAllItems().size();
        Assert.assertEquals(items_size_before, items_size_after);
        Item item = screenPage.findItemWithMainAndDesc(test_main, test_desc);
        Assert.assertNull(item);
    }

    @Test
    public void addItemWithBadValuesTest() throws InterruptedException {
        String bad_name = "";
        String bad_job = "";
        int items_size_before = screenPage.getAllItems().size();
        screenPage.addNewItem(bad_name, bad_job);
        Item new_item = screenPage.findItemWithMainAndDesc(bad_name, bad_job);
        Assert.assertNull(new_item);
        int items_size_after = screenPage.getAllItems().size();
        Assert.assertEquals(items_size_before, items_size_after);
    }
}
