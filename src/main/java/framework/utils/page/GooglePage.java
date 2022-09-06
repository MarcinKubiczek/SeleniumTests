package framework.utils.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class GooglePage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(how = How.XPATH, using = "//*[@id='L2AGLb']/div")
    private WebElement acceptCookieButton;
    @FindBy(how = How.NAME, using = "q")
    private WebElement searchField;
    @FindBy(how = How.NAME, using = "btnI")
    private WebElement luckyButton;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void openGooglePage(){
        driver.get("https://www.google.com/");
    }

    public void acceptCookies(){
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton)).click();
    }

    public void searchText(String text) {
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys(text);
    }

    public void clickOnLuckyShot() {
        wait.until(ExpectedConditions.elementToBeClickable(luckyButton)).click();

    }
}
