package testrunner;

import Pages.LoginPage;
import config.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTestRunner extends Setup {
    @Test(priority = 5,description = "Check successfully login with valid credentials")
    public void doLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
        WebElement imgProfile = driver.findElement(By.className("oxd-userdropdown-img"));
        Assert.assertTrue(imgProfile.isDisplayed());
    }
    @Test(priority = 2,description = "Check unsuccessfully login with invalid credentials")
    public void doLoginWithInvalidCredn() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("admin","admin");
        String alertActualRslt= driver.findElements(By.className("oxd-text")).get(1).getText();
        String alertExpected="Invalid credentials";
        SoftAssert softAssert=new SoftAssert(); //soft assertion
        softAssert.assertTrue(alertActualRslt.contains(alertExpected));
        softAssert.assertAll();
    }

    @Test(priority = 1,description = "Checking unsuccessfully login with blank credentials")
    public void doLoginWithEmptyCredn() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("","");
        String alertActualRslt= driver.findElements(By.className("oxd-text")).get(3).getText();
        String alertExpectedRslt="Required";
        SoftAssert softAssert=new SoftAssert(); //soft assertion
        softAssert.assertTrue(alertActualRslt.contains(alertExpectedRslt));
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Checking unsuccessfully login with invalid username and valid password")
    public void doLoginwithInvalidUsername(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("admin","admin12356");
        String alertActualRslt= driver.findElements(By.className("oxd-text")).get(1).getText();
        String alertExpected="Invalid credentials";
        SoftAssert softAssert=new SoftAssert(); //soft assertion
        softAssert.assertTrue(alertActualRslt.contains(alertExpected));
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Checking unsuccessfully login with valid username and Invalid password")
    public void doLoginwithInvalidPass(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin","admin");
        String alertActualRslt= driver.findElements(By.className("oxd-text")).get(1).getText();
        String alertExpected="Invalid credentials";
        SoftAssert softAssert=new SoftAssert(); //soft assertion
        softAssert.assertTrue(alertActualRslt.contains(alertExpected));
        softAssert.assertAll();
    }
}
