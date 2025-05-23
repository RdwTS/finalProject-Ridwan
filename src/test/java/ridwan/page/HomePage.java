package ridwan.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage {

    WebDriver driver;
    private WebDriverWait wait;

    By navBarLogin = By.id("login2");
    By categories = By.id("cat");
    By navbarLogout = By.id("logout2");
    By productSamsungS6 = By.xpath("//*[@id=\"tbodyid\"]/div[1]/div/div/h4");



    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void validateOnHomePage() {
        driver.get("https://www.demoblaze.com/");
        WebElement categoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(categories));
//        WebElement productElement = driver.findElement(productTitle);
        assertTrue(categoryElement.isDisplayed());
        assertEquals("CATEGORIES", categoryElement.getText());
    }

    public void clickNavBarButton (){
        driver.findElement(navBarLogin).click();
    }
    public void validateAlreadyLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(navbarLogout));
        WebElement logoutNav = wait.until(ExpectedConditions.visibilityOfElementLocated(navbarLogout));
        assertEquals("Log out", logoutNav.getText());
    }

    public void validateNoLogin() throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(navBarLogin));
        WebElement loginNav = wait.until(ExpectedConditions.visibilityOfElementLocated(navBarLogin));
        assertEquals("Log in", loginNav.getText());
    }

    public void clickProductSamsungS6 (){
        WebElement productButton = wait.until(ExpectedConditions.elementToBeClickable(productSamsungS6));
        try {
            productButton.click(); // Klik pakai Selenium
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productButton);// Klik pakai JavaScript
        }
//        driver.findElement(buttonLogin).click();
    }



}
