package Tests;

import Pages.DragPage;
import Pages.FormsPage;
import Pages.LoginPage;
import Pages.SwipePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.DriverManager;
import utilities.PageActionsHelper;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class Tests extends DriverManager {

    AndroidDriver driver;
    WebDriverWait wait;
    LoginPage loginPage = new LoginPage();
    FormsPage formsPage = new FormsPage();
    SwipePage swipePage = new SwipePage();
    DragPage dragPage = new DragPage();
    PageActionsHelper pageActionsHelper = new PageActionsHelper();

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
        pageActionsHelper.performScroll("down");
        wait.until(ExpectedConditions.visibilityOfElementLocated(swipePage.logo));
        Assert.assertTrue(getAppiumDriver().findElement(swipePage.logo).isDisplayed());
        if (getAppiumDriver().findElement(By.xpath("//*[@text='You found me!!!']")).isDisplayed()) {
            System.out.println("I found you!");
        }
    }

    @Test
    public void scrollRightEnd() {
        getAppiumDriver().findElement(swipePage.swipeMenu).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(swipePage.swipePageText));
        List<WebElement> listElementsButton = getAppiumDriver().findElements(swipePage.allButtons);
        for (int i = 0; i < listElementsButton.size(); i++) {
            pageActionsHelper.performScroll("right");
/*
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence sequence = new Sequence(finger1, 0)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger1, Duration.ofMillis(200)))
                    .addAction(finger1.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), endX, endY))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            getAppiumDriver().perform(Collections.singletonList(sequence));

 */
        }
        Assert.assertTrue(getAppiumDriver().findElement(swipePage.lastElement).isDisplayed());
    }

    @Test
    public void dragAndDrop() throws InterruptedException {
        getAppiumDriver().findElement(dragPage.dragMenu).click();

        String leftCenterRight = "lcr";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < leftCenterRight.length(); j++) {
                char c = leftCenterRight.charAt(j);
                WebElement source = getAppiumDriver().findElement(AppiumBy.accessibilityId("drag-" + c + String.valueOf(i)));
                WebElement target = getAppiumDriver().findElement(AppiumBy.accessibilityId("drop-" + c + String.valueOf(i)));

                Point sourceElementCenter = new Point(source.getLocation().getX() + source.getSize().getWidth() / 2,
                        source.getLocation().getY() + source.getSize().getHeight() / 2);
                Point targetElementCenter = new Point(target.getLocation().getX() + target.getSize().getWidth() / 2,
                        target.getLocation().getY() + target.getSize().getHeight() / 2);

                PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
                Sequence sequence = new Sequence(finger1, 1)
                        .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceElementCenter))
                        .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                        .addAction(new Pause(finger1, Duration.ofMillis(200)))
                        .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), targetElementCenter))
                        .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                getAppiumDriver().perform(Collections.singletonList(sequence));
                Thread.sleep(5000);
            }
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}