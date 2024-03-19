package config;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class Setup {
    public WebDriver driver;
    @BeforeTest
    public void setup(){
        driver = new ChromeDriver ();
        driver.manage ().window ().maximize ();
        driver.manage ().timeouts ().implicitlyWait (Duration.ofSeconds (30));
        driver.get ("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    //@AfterTest
    @AfterTest
    public void quitBrowser(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogout();
        driver.quit();
    }
}
