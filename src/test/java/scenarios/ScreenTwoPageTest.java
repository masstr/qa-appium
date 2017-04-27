package scenarios;

import base_classes.Item;
import base_classes.ScreenPageBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddNewItemPage;

import java.util.List;


/**
 * Created by kkarpov on 27.04.17.
 */
public class ScreenTwoPageTest extends ScreenPageBaseTest {

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        screenPage.goToScreenTwo();
        Assert.assertTrue(screenPage.isScreenTwo());
    }

    @Test
    public void editItemTest() {
        int items_size_before = screenPage.getAllItems().size();
        if (items_size_before < 0) {
            screenPage.addNewItem(test_main, test_desc);
        }
        List<Item> items = screenPage.getAllItems();
        items_size_before = items.size();

        Item item_for_edit = items.get(0);
        String main_text_new = item_for_edit.mainText + "Check1";
        String desc_text_new = item_for_edit.descText + "Check2";
        item_for_edit.controlButton.click();

        AddNewItemPage editItemPage = new AddNewItemPage(driver);
        editItemPage.waitForLoad();
        editItemPage.enterMainAndDesc(main_text_new, desc_text_new);
        editItemPage.acceptButton.click();
        Assert.assertTrue(screenPage.isSameScreen());
        int items_size_after = screenPage.getAllItems().size();
        Assert.assertEquals(items_size_before, items_size_after);
        Item new_item = screenPage.findItemWithMainAndDesc(main_text_new, desc_text_new);
        Assert.assertNotNull(new_item);
    }
}