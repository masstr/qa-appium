package scenarios;

import base_classes.Item;
import base_classes.ScreenPageBaseTest;
import org.testng.annotations.*;
import org.testng.Assert;
import pages.RemoveItemPage;

import java.util.List;

/**
 * Created by kkarpov on 26.04.17.
 */

public class ScreenOnePageTest extends ScreenPageBaseTest {

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        Assert.assertTrue(screenPage.isScreenOne());
    }

    @Test
    public void deleteAllItemsTest() throws InterruptedException {
        int items_size_before = screenPage.getAllItems().size();
        if (items_size_before < 0) {
            screenPage.addNewItem(test_main, test_desc);
        }
        for (int i = 0; i < items_size_before; i++) {
            List<Item> items = screenPage.getAllItems();
            Item item = items.get(0);
            item.controlButton.click();
            RemoveItemPage removeItemPage = new RemoveItemPage(driver);
            removeItemPage.waitForLoad();
            removeItemPage.acceptButton.click();
            Assert.assertTrue(screenPage.isSameScreen());
        }
        int items_size_after = screenPage.getAllItems().size();
        Assert.assertEquals(0, items_size_after);
    }
}