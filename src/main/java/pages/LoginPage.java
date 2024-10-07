package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

public class LoginPage extends AndroidActions {

    AndroidDriver driver;

    public LoginPage(AndroidDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"standard_user\"]")
    private WebElement standardUserTxt;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"locked_out_user\"]")
    private WebElement lockedOutUserTxt;

    @AndroidFindBy(accessibility = "test-LOGIN")
    private WebElement loginBtn;

    @AndroidFindBy(accessibility = "test-Error message")
    private WebElement errorMessageBox;

    public void setStandardUser() {
        standardUserTxt.click();
    }

    public void setLockedOutUser() {
        lockedOutUserTxt.click();
    }

    public HomePage clickLoginBtn() {

        loginBtn.click();
        return new HomePage(driver);
    }

    public boolean checkErrorMessageBox() {
        return errorMessageBox.isDisplayed();
    }
}