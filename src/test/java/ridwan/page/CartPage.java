package ridwan.page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage {

    WebDriver driver;
    private WebDriverWait wait;

    By buttonAddCart = By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a");
    By cartNavbar = By.id("cartur");
    By buttonOrder =By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button");
    By buttonPurchase =By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]");
    By inputNameText = By.id("name");
    By inputCountryText = By.id("country");
    By inputCityText = By.id("city");
    By inputCardText = By.id("card");
    By inputMonthText = By.id("month");
    By inputYearText = By.id("year");
    By popUpSuccesPurchase = By.cssSelector(".sweet-alert.showSweetAlert.visible");


    public CartPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    public String getAlertMessage() {
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        alert.accept();
        return message;
    }

    public void userIsOnAddCartPage() throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(buttonAddCart));
        WebElement cartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonAddCart));
        assertEquals("Add to cart", cartButton.getText());
    }

    public void clickAddCartButton (){

        WebElement AddCartButton = wait.until(ExpectedConditions.elementToBeClickable(buttonAddCart));
        try {
            AddCartButton.click(); // Klik pakai Selenium
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddCartButton);// Klik pakai JavaScript
        }
    }

    public void validateGetMessageProductAdd (String message) throws InterruptedException {
        Thread.sleep(1000);
        String actualAlertMessage = getAlertMessage();
        Assertions.assertEquals(actualAlertMessage, message, "Product added.");

    }

    public void clickCartNavbar () throws InterruptedException {
        Thread.sleep(1000);
        WebElement cartNav = wait.until(ExpectedConditions.elementToBeClickable(cartNavbar));
        try {
            cartNav.click(); // Klik pakai Selenium
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartNav);// Klik pakai JavaScript
        }
    }

    public void userIsOnCartPage() throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(buttonOrder));
        WebElement orderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonOrder));
        assertEquals("Place Order", orderButton.getText());
    }

    public void clickOrderButton() {
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(buttonOrder));
        try {
            orderButton.click(); // Klik pakai Selenium
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", orderButton);// Klik pakai JavaScript
        }
    }
    public void inputName (String name) throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputNameText));
        wait.until(ExpectedConditions.presenceOfElementLocated(inputNameText));
        driver.findElement(inputNameText).sendKeys(name);
    }
    public void inputCountry (String country) throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputCountryText));
        wait.until(ExpectedConditions.presenceOfElementLocated(inputCountryText));
        driver.findElement(inputCountryText).sendKeys(country);
    }
    public void inputCity (String city) throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputCityText));
        wait.until(ExpectedConditions.presenceOfElementLocated(inputCityText));
        driver.findElement(inputCityText).sendKeys(city);
    }
    public void inputCard (String card) throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputCardText));
        wait.until(ExpectedConditions.presenceOfElementLocated(inputCardText));
        driver.findElement(inputCardText).sendKeys(card);
    }
    public void inputMonth (String month) throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputMonthText));
        wait.until(ExpectedConditions.presenceOfElementLocated(inputMonthText));
        driver.findElement(inputMonthText).sendKeys(month);
    }
    public void inputYear (String year) throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputYearText));
        wait.until(ExpectedConditions.presenceOfElementLocated(inputYearText));
        driver.findElement(inputYearText).sendKeys(year);
    }

    public void clickButtonPurchase () throws InterruptedException {
        Thread.sleep(1000);
        WebElement purchaseButton = wait.until(ExpectedConditions.elementToBeClickable(buttonPurchase));
        try {
            purchaseButton.click(); // Klik pakai Selenium
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", purchaseButton);// Klik pakai JavaScript
        }
    }


    public void validateGetMessageOrderSucces (String orderMessage) throws InterruptedException {
        Thread.sleep(1000);
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(popUpSuccesPurchase));
        String fullText = popup.getText();
        Assertions.assertTrue(fullText.contains(orderMessage), "Expected message not found in popup! Found text: " + fullText);
    }






}
