package testrunner;

import Pages.LoginPage;
import config.Setup;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class EmployeeProfileTestRunner extends Setup {
    @Test(priority = 4, description="Successfully new created employee with valid credentials")
    public void empLoginByValidCred() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage (driver);
        JSONObject jsonObject = Utils.getUsers ();
        String username=(String)jsonObject.get ("username");
        String password=(String) jsonObject.get ("password");
        loginPage.doLogin(username,password);

        WebElement imgProfile=driver.findElement (By.className ("oxd-userdropdown-img"));
        Assert.assertTrue (imgProfile.isDisplayed ());
    }

    @Test(priority = 5, description = "Check that the employee's full name is showing besides the profile icon")
    public void assertProfilePic(){
        WebElement actualFullName=driver.findElement (By.className ("oxd-userdropdown-name"));
        Assert.assertTrue (actualFullName.isDisplayed ());
    }
    @Test(priority = 1, description="Check if Unsuccessfully new created employee with Invalid credentials")
    public void empLoginByInValidCred() throws IOException, ParseException, InterruptedException {
        LoginPage loginPage = new LoginPage (driver);
        String username= "blank";
        String password = "blank";
        loginPage.doLogin(username,password);

        String alertActual = driver.findElements(By.className("oxd-text")).get(1).getText();
        String alertExpected = "Invalid credentials";
        Thread.sleep (1000);
        Assert.assertTrue(alertActual.contains(alertExpected));
    }

    @Test(priority = 2, description="Check if Unsuccessfully new created employee with Invalid username and valid password")
    public void empLoginByInValidUseName() throws IOException, ParseException, InterruptedException {
        LoginPage loginPage = new LoginPage (driver);
        JSONObject jsonObject = Utils.getUsers ();
        String username= "blank";
        String password = (String) jsonObject.get ("password");
        loginPage.doLogin(username,password);

        String alertActual = driver.findElements(By.className("oxd-text")).get(1).getText();
        String alertExpected = "Invalid credentials";
        Thread.sleep (1000);
        Assert.assertTrue(alertActual.contains(alertExpected));
    }
    @Test(priority = 3, description="Check if Unsuccessfully new created employee with valid username and Invalid password")
    public void empLoginByInValidPassword() throws IOException, ParseException, InterruptedException {
        LoginPage loginPage = new LoginPage (driver);
        JSONObject jsonObject = Utils.getUsers ();
        String username= (String) jsonObject.get ("username");
        String password = "blank";
        loginPage.doLogin(username,password);

        String alertActual = driver.findElements(By.className("oxd-text")).get(1).getText();
        String alertExpected = "Invalid credentials";
        Thread.sleep (1000);
        Assert.assertTrue(alertActual.contains(alertExpected));
    }

    @Test(priority = 6, description= "Go to my info,Scroll down and select Gender and Blood Type as O+ and save it")
    public void updateGenderInfo(){
        WebElement myInfo=driver.findElements (By.className ("oxd-main-menu-item-wrapper")).get(2);
        myInfo.click (); //Go to My Info
        List<WebElement> title = driver.findElements (By.className ("orangehrm-main-title"));
        Utils.waitForElement(driver, title.get(0));
        WebElement gender = driver.findElements(By.className("oxd-radio-input")).get(1);
        gender.click();
        Utils.doScroll(driver, 0, 300);
        WebElement bloodtype = driver.findElements(By.className("oxd-select-text-input")).get(2);
        bloodtype.click();
        Actions actions = new Actions (driver);
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();
        WebElement savebtn = driver.findElements(By.className("oxd-button")).get(1);
        savebtn.click();
    }
}