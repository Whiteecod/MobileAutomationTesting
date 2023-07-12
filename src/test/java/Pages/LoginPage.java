package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverManager;

public class LoginPage extends DriverManager {
     AndroidDriver driver = getAppiumDriver();

     public void loginPage() {
         PageFactory.initElements(driver , this);
     }

    public AppiumBy loginMenu =new AppiumBy.ByAccessibilityId("Login");
    public AppiumBy usernameInputField =new AppiumBy.ByAccessibilityId("input-email");
    public AppiumBy passwordInputField =new AppiumBy.ByAccessibilityId("input-password");
    public AppiumBy loginButton =new AppiumBy.ByAccessibilityId("button-LOGIN");
    public By successfulLoginMessage = By.id("android:id/message");
    public By loginOkButton =new By.ById("android:id/button1");
}
