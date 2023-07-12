package Tests;

import Pages.DragPage;
import Pages.FormsPage;
import Pages.LoginPage;
import Pages.SwipePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.DriverManager;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class LoginTest extends DriverManager {

    AndroidDriver driver;
    WebDriverWait wait;
    LoginPage loginPage = new LoginPage();
    FormsPage formsPage = new FormsPage();
    SwipePage swipePage = new SwipePage();
    DragPage dragPage = new DragPage();

    @BeforeTest
    public void setUp() {
        driver = getAppiumDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void LoginFunctionTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(getAppiumDriver(), Duration.ofSeconds(20));
        Thread.sleep(2000);
        getAppiumDriver().findElement(loginPage.loginMenu).click();
        getAppiumDriver().findElement(loginPage.usernameInputField).sendKeys("username123@gmail.com");
        getAppiumDriver().findElement(loginPage.passwordInputField).sendKeys("parola123!");
        getAppiumDriver().findElement(loginPage.loginButton).click();
        String actualText = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.successfulLoginMessage)).getText();
        System.out.println("Login message = " + actualText);
        Assert.assertEquals(actualText, "You are logged in!");
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginOkButton));
        getAppiumDriver().findElement(loginPage.loginOkButton).click();
        Thread.sleep(3000);
    }

    @Test
    public void Dropdown() {
        getAppiumDriver().findElement(formsPage.formsButton).click();
        getAppiumDriver().findElement(formsPage.dropdownButton).click();
        getAppiumDriver().findElement(formsPage.secondOption).click();
        System.out.println("Selected option is = " + getAppiumDriver().findElement(formsPage.actualSelectedOption).getText());
        getAppiumDriver().findElement(formsPage.dropdownButton).click();
        String isSelected = getAppiumDriver().findElement(formsPage.secondOption).getAttribute("checked");
        Assert.assertEquals(isSelected, "true");
        System.out.println("Is element selected = " + isSelected);
    }

    @Test
    public void Switch() {
        getAppiumDriver().findElement(formsPage.formsButton).click();
        String switchTextBeforeClick = getAppiumDriver().findElement(formsPage.switchText).getText();
        Assert.assertEquals(switchTextBeforeClick, "Click to turn the switch ON");
        System.out.println("Switch Text Before Click= " + switchTextBeforeClick);
        getAppiumDriver().findElement(formsPage.switchButton).click();
        String switchTextAfterClick = getAppiumDriver().findElement(formsPage.switchText).getText();
        Assert.assertEquals(switchTextAfterClick, "Click to turn the switch OFF");
        System.out.println("Switch Text After Click = " + switchTextAfterClick);
    }

    @Test
    public void scrollDown() {
        getAppiumDriver().findElement(swipePage.swipeMenu).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(swipePage.swipePageText));
        Dimension size = getAppiumDriver().manage().window().getSize();
        System.out.println("size = " + size);
        int startX = size.getWidth() / 2;
        int startY = (int) (size.getHeight() * 0.8);
        int endX = startX;
        int endY = (int) (size.getHeight() * 0.1);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 0)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getAppiumDriver().perform(Collections.singletonList(sequence));
    }

    @Test
    public void scrollRight() {
        getAppiumDriver().findElement(swipePage.swipeMenu).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(swipePage.swipePageText));
        Dimension size = getAppiumDriver().manage().window().getSize();
        System.out.println("size = " + size);
        int startX = (int) (size.getWidth() * 0.9);
        int startY = (int) (size.getHeight() * 0.8 );
        int endX = (int) (size.getWidth() * 0.1);
        int endY = startY;

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 0)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getAppiumDriver().perform(Collections.singletonList(sequence));
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}