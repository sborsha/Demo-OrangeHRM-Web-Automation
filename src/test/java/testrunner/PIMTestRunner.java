package testrunner;

import Pages.LoginPage;
import Pages.PIMPage;
import com.github.javafaker.Faker;
import config.Setup;
import config.UsersModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class PIMTestRunner extends Setup {
    @BeforeTest
    public void doLogin() {
        LoginPage loginPage = new LoginPage (driver);
        LoginPage.doLogin ("Admin", "admin123");
    }

    @Test(priority = 1, description = "Create New Employee with valid details")
    public void employeeRegistration() throws InterruptedException, IOException, ParseException {
        PIMPage pimPage = new PIMPage (driver);
        Faker faker = new Faker ();
        String firstName = faker.name ().firstName ();
        String lastName = faker.name ().lastName ();
        String username = faker.name ().username ();
        String password = Utils.generateRandomPass ();

        pimPage.registerUser (firstName, lastName, username, password);
        Utils.doScroll (driver, 0, 500);

        Thread.sleep (5000);

        String assertMessage = driver.findElements (By.className ("oxd-text--h6")).get (2).getText ();
        Assert.assertTrue (assertMessage.contains ("Personal Details"));

        Thread.sleep (5000);

        List<WebElement> id = driver.findElements (By.className ("oxd-input"));
        String empID=id.get (4).getAttribute ("value");
        Thread.sleep (3000);


        UsersModel usersModels = new UsersModel ();
        usersModels.setFirstname (firstName);
        usersModels.setLastname (lastName);
        usersModels.setEmpID (empID);
        usersModels.setUsername (username);
        usersModels.setPassword (password);
        Utils.saveUsers (usersModels);
    }
    @Test(priority = 2, description = "Verify that the employee's created successfully")
    public void employeeCreatedSuccessfully() {
        String actualmessage = driver.findElements(By.className("orangehrm-main-title")).get(0).getText();
        Assert.assertTrue(actualmessage.contains("Personal Details"));
    }

    @Test(priority = 3, description = "Verify unsuccessful creation of a new employee with a duplicate employee ID or username.")
    public void employeeRegistrationWithMissingSomeField() throws InterruptedException, IOException, ParseException {
        PIMPage pimPage = new PIMPage(driver);
        Faker faker = new Faker();
        String firstName = "";
        String lastName = faker.name().lastName();
        String username = faker.name ().username ();
        String password = Utils.generateRandomPass();

        pimPage.registerUser(firstName, lastName, username, password);
        Utils.doScroll(driver, 0, 500);

//        Thread.sleep (2000);
        String titleActual = driver.findElement(By.xpath ("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[1]/span")).getText();
        String expectedResult = "Required";
        Thread.sleep (1000);
        Assert.assertTrue(titleActual.contains(expectedResult));
    }
    @Test(priority = 4, description = "Verify unsuccessful creation of a new employee with a duplicate employee ID or username.")
    public void employeeRegistrationWithDuplicateUsername() throws InterruptedException, IOException, ParseException {
        PIMPage pimPage = new PIMPage(driver);
        JSONObject jsonObject = Utils.getUsers ();
        String duplicateUsername = (String) jsonObject.get("username");
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = duplicateUsername;
        String password = Utils.generateRandomPass();

        pimPage.registerUser(firstName, lastName, username, password);
        Utils.doScroll(driver, 0, 500);

//        Thread.sleep (2000);
        String titleActual = driver.findElement(By.xpath ("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/span")).getText();
        String expectedResult = "Username already exists";
        Thread.sleep (1000);
        Assert.assertTrue(titleActual.contains(expectedResult));
    }

    @Test(priority = 5, description = "Search Employee with Valid ID and get proper value")
    public void searchUserWithValidEmpId() throws IOException, ParseException, InterruptedException {
        PIMPage pimPage = new PIMPage (driver);
        JSONObject jsonObject = Utils.getUsers ();
        String empID = (String) jsonObject.get ("empID");
        pimPage.searchByEmployeeId (empID);
        Thread.sleep (2000);
        String titleActual = driver.findElements(By.className("oxd-text")).get(14).getText();
        String expectedResult = "(1) Record Found";
        Thread.sleep (2000);
        Assert.assertTrue(titleActual.contains(expectedResult));
    }

    @Test(priority = 6, description = "Search Employee with InValid ID")
    public void searchUserWithInValidEmpId() throws IOException, ParseException, InterruptedException {
        PIMPage pimPage = new PIMPage (driver);
        String empID = "4444";
        pimPage.searchByEmployeeId (empID);
        Thread.sleep (2000);
        String titleActual = driver.findElements(By.className("oxd-text")).get(14).getText();
        String expectedResult = "No Records Found";
        Thread.sleep (2000);
        Assert.assertTrue(titleActual.contains(expectedResult));
    }

    @Test(priority = 7, description = "Search Employee by valid Name")
    public void searchUserWithEmpName() throws IOException, ParseException, InterruptedException {
        PIMPage pimPage = new PIMPage(driver);
        JSONObject jsonObject = Utils.getUsers();
        String empName = (String) jsonObject.get("firstName");
        pimPage.searchByEmployeeName (empName);
        Thread.sleep (2000);
        String titleActual = driver.findElements(By.className("oxd-text")).get(14).getText();
        String expectedResult = "(1) Record Found";
        Assert.assertTrue(titleActual.contains(expectedResult));

    }

    @Test(priority = 8, description = "Search Employee by Invalid Name")
    public void searchUserWithInEmpName() throws IOException, ParseException, InterruptedException {
        PIMPage pimPage = new PIMPage(driver);
        String empName= "blank";
        pimPage.searchByEmployeeName (empName);
        Thread.sleep (2000);
        String titleActual = driver.findElements(By.className("oxd-text")).get(14).getText();
        String expectedResult = "Invalid";
        Assert.assertTrue(titleActual.contains(expectedResult));

    }
}
