package framework.utils.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WikiPage {
    private WebDriver driver;
    @FindBy(how = How.XPATH, using = "//*[@id=\"p-lang\"]/div/ul/li/a")
    private List<WebElement> languageList;

    public WikiPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openWikiPage() {
        driver.get("https://pl.wikipedia.org/wiki/Wiki");
    }

    public void printAllLanguages() {
        for (WebElement element : languageList) {
            String languageName = element.getText();
            if (languageName.equals("English")) {
                System.out.println(languageName + " " + element.getAttribute("href"));
            } else {
                System.out.println(languageName);
            }
        }
    }
}
