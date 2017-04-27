package base_classes;

import org.openqa.selenium.WebElement;

/**
 * Created by kkarpov on 27.04.17.
 */
public class Item {
    public String mainText;
    public String descText;
    public WebElement controlButton;

    public Item(String mainText, String descText, WebElement controlButton) {
        this.mainText = mainText;
        this.descText = descText;
        this.controlButton = controlButton;
    }
}
