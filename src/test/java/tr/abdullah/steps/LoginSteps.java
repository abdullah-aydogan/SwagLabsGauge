package tr.abdullah.steps;

import com.thoughtworks.gauge.Step;
import org.testng.Assert;
import pages.HomePage;
import tr.abdullah.base.BaseTest;

import java.io.IOException;

public class LoginSteps extends BaseTest {

    public HomePage homePage;

    @Step("SwagLabs uygulamasini ac")
    public void openApp() throws IOException {
        configureAppium();
    }

    @Step("Giris ekranindaki <string> yazisina tikla")
    public void setStandardUser(String userType) {

        if(userType.contains("standard")) {
            loginPage.setStandardUser();
        }

        else if(userType.contains("locked")) {
            loginPage.setLockedOutUser();
        }
    }

    @Step("Giris yap butonuna tikla")
    public void clickLoginBtn() {
        homePage = loginPage.clickLoginBtn();
    }

    @Step("Ana ekranin acildigini kontrol et")
    public void checkProductsTitle() {
        Assert.assertTrue(homePage.checkProductsTitle());
    }

    @Step("Hata mesajinin geldigini kontrol et")
    public void checkErrorMessage() {
        Assert.assertTrue(loginPage.checkErrorMessage());
    }

    @Step("<string> gerekli uyarisini kontrol et")
    public void checkEmptyField(String fieldName) {

        if(fieldName.contains("Username")) {
            Assert.assertTrue(loginPage.checkUsername());
        }

        else if(fieldName.contains("Password")) {
            Assert.assertTrue(loginPage.checkPassword());
        }
    }

    @Step("Kullanici adi alanina <string> yaz")
    public void enterUsername(String text) {
        loginPage.enterUsername(text);
    }
}