package ridwan.page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    private WebDriverWait wait;

    By inputUsernameText = By.id("loginusername");
    By inputPasswordText = By.id("loginpassword");
    By buttonLogin = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");

    public String getAlertMessage() {
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        alert.accept();
        return message;
    }

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void inputUsername (String username) throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUsernameText));
        wait.until(ExpectedConditions.presenceOfElementLocated(inputUsernameText));
        driver.findElement(inputUsernameText).sendKeys(username);
    }

    public void inputPassword (String password) throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(inputPasswordText));
        driver.findElement(inputPasswordText).sendKeys(password);
    }

    public void clickLoginButton (){
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(buttonLogin));
        try {
            loginButton.click(); // Klik pakai Selenium
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);// Klik pakai JavaScript
        }
//        driver.findElement(buttonLogin).click();
    }

    public void validateGetErrorMessage (String errMessage) throws InterruptedException {
        Thread.sleep(1000);
        String actualAlertMessage = getAlertMessage();
        Assertions.assertEquals(actualAlertMessage, errMessage, "Alert message not as expected!");

    }



}
