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

public class W3Page {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(how = How.ID , using = "accept-choices")
    private WebElement acceptCookieButton;
    @FindBy(how = How.XPATH , using = "//a[text()[contains(.,'Try it Yourself »')]]")
    private WebElement tryItYourselfButton;


    public W3Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean verifyURl(String url) {
        String currentUrl = driver.getCurrentUrl();
        if(!currentUrl.equals(url)){
            System.out.println(currentUrl);
            driver.get(url);
        }
       return driver.getCurrentUrl().equals(url);
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton)).click();
    }

    public void tryItYourself() {
        wait.until(ExpectedConditions.elementToBeClickable(tryItYourselfButton)).click();
    }
}
