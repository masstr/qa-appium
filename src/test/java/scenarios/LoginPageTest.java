package scenarios;

import base_classes.AndroidSetup;
import org.testng.annotations.*;
import pages.LoginPage;
import org.testng.Assert;

/**
 * Created by kkarpov on 26.04.17.
 */

public class LoginPageTest extends AndroidSetup {
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() throws Exception {
        prepareAndroidForAppium();
        loginPage = new LoginPage(driver).waitForLoad();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
        driver = null;
        loginPage = null;
    }

    @Test
    public void validLoginTest() throws InterruptedException {
        loginPage.loginWithEmailAndPassword("sometest@email.ru","12345");
        Assert.assertFalse(loginPage.isStillLoginPage());
    }

    @Test
    public void invalidLoginTest() throws InterruptedException {
        String invalid_email_text = "sometestru";
        String invalid_password_text = "123";
        loginPage.loginWithEmailAndPassword(invalid_email_text, invalid_password_text);
        Assert.assertTrue(loginPage.isStillLoginPage());
    }

    @Test
    public void clickableStatusSignInButtonTest() throws InterruptedException {
        Assert.assertFalse(loginPage.siginButton.isEnabled());
        loginPage.enterEmail("sometext");
        Assert.assertFalse(loginPage.siginButton.isEnabled());
        loginPage.emailField.clear();
        loginPage.enterPassword("123");
        Assert.assertFalse(loginPage.siginButton.isEnabled());
        loginPage.enterEmail("sometext");
        Assert.assertTrue(loginPage.siginButton.isEnabled());
        Assert.assertTrue(loginPage.siginButton.isEnabled());
        loginPage.emailField.clear();
        loginPage.passwordField.clear();
        Assert.assertFalse(loginPage.siginButton.isEnabled());
    }
}
