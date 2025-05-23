package ridwan;

import org.testng.annotations.Test;
import ridwan.page.HomePage;
import ridwan.page.LoginPage;

/**
 * @Project  : ridwan.LoginTest
 *  @author   : Ridwan
 *  @Description : Using TestNG
 */

public class LoginTest extends BaseTest{

        @Test
        public void loginTest() throws InterruptedException {
//        WebDriver driverChrome = WebDriverManager.chromedriver().create();
//        driverChrome.manage().window().maximize();

        getDriver();
        driver.get("https://www.demoblaze.com/");
        HomePage homePage = new HomePage(driver);

        LoginPage loginPage = new LoginPage(driver);

        homePage.clickNavBarButton();
        loginPage.inputUsername("logintes123");
        loginPage.inputPassword("logintes123");
        loginPage.clickLoginButton();
        }

}
