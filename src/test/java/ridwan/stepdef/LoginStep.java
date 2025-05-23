package ridwan.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ridwan.page.HomePage;
import ridwan.page.LoginPage;
import ridwan.BaseTest;

public class LoginStep extends BaseTest{

    LoginPage loginPage;
    HomePage homePage;

    @Given("user is on home page")
    public void userIsOnHomepage(){
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        homePage.validateOnHomePage();
    }


    @And("user click login bar")
    public void userClickLoginBar() {
        homePage.clickNavBarButton();
    }

    @And("user input username with {string}")
    public void userInputUsername(String username) throws InterruptedException {
        loginPage.inputUsername(username);
    }

    @And("user input password with {string}")
    public void userInputPassword(String password) throws InterruptedException{
        loginPage.inputPassword(password);
    }

    @When("user click login button")
    public void userClickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("user will be on homepage with login user")
    public void userWillBeOnHomepageWithLoginUser() {
        homePage.validateAlreadyLogin();
    }

    @Then("user able to see error message {string}")
    public void userAbleToSeeErrorMessage(String errMessage) throws InterruptedException {
        loginPage.validateGetErrorMessage(errMessage);
    }

    @And("user will be on homepage with no login user")
    public void userWillBeOnHomepageWithNoLoginUser() throws InterruptedException {
        homePage.validateNoLogin();
    }
}
