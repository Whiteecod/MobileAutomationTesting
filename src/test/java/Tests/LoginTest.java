package Tests;

import Utility.DriverManage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginTest extends DriverManage {

    AndroidDriver driver;
    WebDriverWait wait;


    @BeforeTest
    public void setUp() {
        driver = getAppiumDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    static AppiumBy loginMenu = new AppiumBy.ByAccessibilityId("Login");
    static AppiumBy usernameInputField = new AppiumBy.ByAccessibilityId("input-email");
    static AppiumBy passwordInputField = new AppiumBy.ByAccessibilityId("input-password");
    static AppiumBy loginButton = new AppiumBy.ByAccessibilityId("button-LOGIN");
    static By loginOkButton = new By.ById("android:id/button1");
    static By succesMessage = new By.ById("android:id/message");

    @Test
    public static void LoginFunctionTest() throws InterruptedException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setPlatformVersion("11.0");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setDeviceName("Emulator");
        options.setAppPackage("com.wdiodemoapp");
        options.setAppActivity("com.wdiodemoapp.MainActivity");


        WebDriverWait wait = new WebDriverWait(getAppiumDriver(), Duration.ofSeconds(20));
        Thread.sleep(2000);
        getAppiumDriver().findElement(loginMenu).click();
        getAppiumDriver().findElement(usernameInputField).sendKeys("username123@gmail.com");
        getAppiumDriver().findElement(passwordInputField).sendKeys("parola123!");
        getAppiumDriver().findElement(loginButton).click();

        String actualText = wait.until(ExpectedConditions.visibilityOfElementLocated(succesMessage)).getText();
        System.out.println(actualText);
        Assert.assertEquals(actualText , "You are logged in!");
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginOkButton));
        getAppiumDriver().findElement(loginOkButton).click();
        Thread.sleep(3000);

        getAppiumDriver().quit();

    }

    @Test
    public static void dropDown(){
        AppiumBy formsButtton = (AppiumBy) AppiumBy.accessibilityId("Forms");
        getAppiumDriver().findElement(formsButtton).click();
        AppiumBy dropDown = (AppiumBy) AppiumBy.accessibilityId("Dropdown");
        getAppiumDriver().findElement(dropDown).click();
        By secondOption = By.xpath("//*[@text='Appium is awesome']");
        getAppiumDriver().findElement(secondOption).click();
        String isSelected = getAppiumDriver().findElement(secondOption).getAttribute("checked");
        Assert.assertEquals(isSelected,"false");
        System.out.println("Is element selected" + isSelected);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            getAppiumDriver().quit();
        }
    }
}