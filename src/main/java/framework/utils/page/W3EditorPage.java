package framework.utils.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class W3EditorPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(how = How.TAG_NAME , using = "h1")
    private WebElement headerElement;
    @FindBy(how = How.ID , using = "cars")
    private WebElement carSelectElement;

    public W3EditorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public String getHeaderText() {
        switchToResultiFrame();
        return wait.until(ExpectedConditions.visibilityOf(headerElement)).getText();
    }

    public void selectFromDropdown(String carName) {
        wait.until(ExpectedConditions.visibilityOf(carSelectElement));
        Select dropdown = new Select(carSelectElement);
        dropdown.selectByValue(carName);

    }

    private void switchToResultiFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeResult"));
    }

    public WebElement getSelectedElement(){
        wait.until(ExpectedConditions.visibilityOf(carSelectElement));
        Select dropdown = new Select(carSelectElement);
        return dropdown.getFirstSelectedOption();
    }

    public void printNameAndValue(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        System.out.println(element.getText() + "," + element.getAttribute("value"));
    }
}
