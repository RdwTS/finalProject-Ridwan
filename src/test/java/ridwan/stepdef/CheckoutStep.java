package ridwan.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ridwan.BaseTest;
import ridwan.page.CartPage;
import ridwan.page.HomePage;

public class CheckoutStep extends BaseTest {

    HomePage homePage;
    CartPage cartPage;

    @Given("user click product")
    public void userClickProduct(){
        homePage = new HomePage(driver);
//        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        homePage.clickProductSamsungS6();
    }

    @When("user is on add cart page")
    public void userIsOnAddCartPage() throws InterruptedException {
        cartPage.userIsOnAddCartPage();

    }

    @Then("user click add to cart")
    public void userClickAddToCart() {
        cartPage.clickAddCartButton();
    }

    @Then("user will be get message {string}")
    public void userWillBeGetMessage(String message) throws InterruptedException {
    cartPage.validateGetMessageProductAdd(message);
    }

    @When("user click cart bar")
    public void userClickCartBar() throws InterruptedException {
        cartPage.clickCartNavbar();
    }

    @Then("user will be on cart pages")
    public void userWillBeOnCartPages() throws InterruptedException {
        cartPage.userIsOnCartPage();
    }

    @And("user click order button")
    public void userClickOrderButton() {
        cartPage.clickOrderButton();
    }

    @And("user enters name {string}")
    public void userEntersName(String name) throws InterruptedException {
        cartPage.inputName(name);
    }

    @And("user enters country {string}")
    public void userEntersCountry(String country) throws InterruptedException{
        cartPage.inputCountry(country);
    }

    @And("user enters city {string}")
    public void userEntersCity(String city) throws InterruptedException{
        cartPage.inputCity(city);
    }

    @And("user enters creditCard {string}")
    public void userEntersCreditCard(String creditCard) throws InterruptedException{
        cartPage.inputCard(creditCard);
    }

    @And("user enters month {string}")
    public void userEntersMonth(String month) throws InterruptedException{
        cartPage.inputMonth(month);
    }

    @And("user enters year {string}")
    public void userEntersYear(String year) throws InterruptedException{
        cartPage.inputYear(year);
    }

    @When("user clicks the purchase button")
    public void userClicksThePurchaseButton() throws InterruptedException {
        cartPage.clickButtonPurchase();
    }

    @Then("user should see a confirmation message {string} with information order")
    public void userShouldSeeAConfirmationMessageWithInformationOrder(String orderMessage) throws InterruptedException {
        cartPage.validateGetMessageOrderSucces(orderMessage);
    }
}
