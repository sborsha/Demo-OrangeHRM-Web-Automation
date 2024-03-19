package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(name="username")
    static WebElement txtUsername;
    @FindBy(name="password")
    static WebElement txtPassword;
    @FindBy(css="[type=\"submit\"]")
    static WebElement btnLogin;
    @FindBy(className = "oxd-userdropdown-name")
    WebElement loggedinUser;
    @FindBy(css="[role=\"menuitem\"]")
    List<WebElement> btnLogout;

    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements (driver,this);

    }

    public static void doLogin(String username, String password){
        txtUsername.sendKeys (username);
        txtPassword.sendKeys (password);
        btnLogin.click ();
    }
    public void doLogout(){
        LoginPage loginPage = new LoginPage (driver);
        loginPage.loggedinUser.click ();
        loginPage.btnLogout.get(3).click ();
    }

}
