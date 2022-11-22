package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before

    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        // Enter username field
        sendTextToElements(By.id("username"), "tomsmith");
        // Enter password
        sendTextToElements(By.id("password"), "SuperSecretPassword!");

        clickOnElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));

        String expectMessage = "Secure Area";

        //Find the text element and get the text
        WebElement actualTextMessageElement = driver.findElement(By.xpath("//h2[normalize-space()='Secure Area']"));
        String actualMessage = actualTextMessageElement.getText();

        // Validate actual and expected message
        Assert.assertEquals("No such message displayed", expectMessage, actualMessage);

    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        // Enter username field
        sendTextToElements(By.id("username"), "tomsmith1");
        // Enter password
        sendTextToElements(By.id("password"), "SuperSecretPassword!");

        clickOnElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));

        String expectMessage = "Your username is invalid!\n" +
                "×";

        //Find the text element and get the text
        WebElement actualTextMessageElement = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualMessage = actualTextMessageElement.getText();

        // Validate actual and expected message
        Assert.assertEquals("No such message displayed", expectMessage, actualMessage);

    }

    @Test
    public void verifyThePasswordErrorMessage() {
        // Enter username field
        sendTextToElements(By.id("username"), "tomsmith");
        // Enter password
        sendTextToElements(By.id("password"), "SuperSecretPassword");

        clickOnElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));

        String expectMessage = "Your password is invalid!\n" +
                "×";

        //Find the text element and get the text
        WebElement actualTextMessageElement = driver.findElement(By.xpath("//div[@class = 'flash error']"));
        String actualMessage = actualTextMessageElement.getText();

        // Validate actual and expected message
        Assert.assertEquals("No such message displayed", expectMessage, actualMessage);

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
