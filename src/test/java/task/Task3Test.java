package task;

import framework.utils.DriverFactory;
import framework.utils.page.GooglePage;
import framework.utils.page.W3EditorPage;
import framework.utils.page.W3Page;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Task3Test {

    @Test
    public void Task3Test(){
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();
        GooglePage googlePage = new GooglePage(webDriver);
        googlePage.openGooglePage();
        googlePage.acceptCookies();
        googlePage.searchText("HTML select tag - W3Schools");
        googlePage.clickOnLuckyShot();
        W3Page w3Page = new W3Page(webDriver);
        Assert.assertTrue(w3Page.verifyURl("https://www.w3schools.com/tags/tag_select.asp"));
        w3Page.acceptCookies();
        w3Page.tryItYourself();
        W3EditorPage w3EditorPage = new W3EditorPage(webDriver);
        ArrayList<String> currentWindow = new ArrayList<> (webDriver.getWindowHandles());
        webDriver.switchTo().window(currentWindow.get(1));
        String headerText = w3EditorPage.getHeaderText();
        System.out.println(headerText);
        w3EditorPage.selectFromDropdown("opel");
        WebElement element = w3EditorPage.getSelectedElement();
        w3EditorPage.printNameAndValue(element);
        webDriver.quit();
    }
}
