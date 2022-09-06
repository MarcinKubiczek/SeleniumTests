package task;

import framework.utils.DriverFactory;
import framework.utils.page.WikiPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;



public class Task2Test {
    @Test
    public void Task2Test(){
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();
        WikiPage wiki = new WikiPage(webDriver);
        wiki.openWikiPage();
        wiki.printAllLanguages();
        webDriver.quit();
    }
}
