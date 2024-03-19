package Pages;

import config.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMPage extends Setup {
    @FindBy(className = "oxd-main-menu-item")
    List<WebElement> menuItems;
    @FindBy(className = "oxd-button")
    List<WebElement> button;

    @FindBy(className = "oxd-input")
    List<WebElement>inputField;

    @FindBy(tagName = "input")
    List<WebElement>employeeName;
    @FindBy(css = "[role=\"listbox\"]")
    WebElement selectRole;

    @FindBy(className = "oxd-switch-input")
    WebElement toggleButton;

    public PIMPage (WebDriver driver){
        PageFactory.initElements (driver, this);
    }
    public void registerUser(String firstName, String lastName, String username, String password){
        menuItems.get (1).click (); //Click on PIM Page
        button.get (2).click (); //click on Add button
        toggleButton.click ();

        inputField.get (1).sendKeys (firstName);  //type First Name
        inputField.get (3).sendKeys (lastName);  //type Last Name
        inputField.get (5).sendKeys (username);
        inputField.get (6).sendKeys (password);
        inputField.get (7).sendKeys (password); //confirm password
        button.get (1).click ();//click on save button
    }

    public void searchByEmployeeId(String emplyId) throws InterruptedException {
        menuItems.get (1).click ();
        inputField.get (1).sendKeys (emplyId);
        Thread.sleep(1000);
        button.get (1).click ();
    }

    public void searchByEmployeeName(String empName) throws InterruptedException {
        menuItems.get(8).click();
        employeeName.get(1).sendKeys(empName);
        Thread.sleep(2000);
        selectRole.click();
        button.get(1).click();
    }
}
