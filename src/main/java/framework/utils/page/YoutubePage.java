package framework.utils.page;

import framework.utils.DTO.YouTubeVideoDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class YoutubePage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(how = How.CSS , using = "tp-yt-paper-button[aria-label='Accept the use of cookies and other data for the purposes described']")
    private WebElement acceptCookieButton;
    //This xpath is created because of an issue with YoutTubeShorts
    @FindBy(how = How.XPATH, using = "//yt-formatted-string[@id='video-title']/../../../../../..")
    private List<WebElement> videoList;
    private By titleXpath = By.xpath(".//yt-formatted-string[@id='video-title']");
    private By channelXPath =  By.xpath(".//yt-formatted-string[@id=\"text\"]/a");
    private By timeSelector = By.cssSelector("ytd-thumbnail-overlay-time-status-renderer.style-scope.ytd-thumbnail span.style-scope.ytd-thumbnail-overlay-time-status-renderer#text");

    public YoutubePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public void openYoutubePage(){
        driver.get("https://www.youtube.com/");
    }
    public void acceptCookies(){
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton)).click();
    }

    public List<YouTubeVideoDTO> assignList(int resultLimit, List<YouTubeVideoDTO> inputList) {
        waitUntilCountChanges();
        wait.until(ExpectedConditions.presenceOfElementLocated(timeSelector));
        for(int i=0; i<resultLimit; i ++){
            YouTubeVideoDTO ytVideo = new YouTubeVideoDTO();
            ytVideo.setTitle(videoList.get(i).findElement(titleXpath).getText());
            ytVideo.setChannel(videoList.get(i).findElement(channelXPath).getText());

            Boolean isPresent = videoList.get(i).findElements(timeSelector).size()>0;
            if(isPresent){
                WebElement timer = videoList.get(i).findElement(timeSelector);
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", timer);
                waitForTimerToApear(timer);
                ytVideo.setLength(timer.getText());
            }
            else{
                ytVideo.setLength("live");
            }
            inputList.add(ytVideo);
        }
        return inputList;
    }


    public void printNameAndNotLiveTitles(List<YouTubeVideoDTO> ytTileList) {
        ytTileList.stream().filter(s->!s.getLength().equals("live"))
                .forEach(s-> System.out.println(s.getTitle()+ " " + s.getLength()));
    }

    private void waitUntilCountChanges() {
        wait.until((ExpectedCondition<Boolean>) driver -> {
            int elementCount = videoList.size();
            return elementCount > 1;
        });
    }
    private void waitForTimerToApear(WebElement element) {
        wait.until((ExpectedCondition<Boolean>) driver ->{
            int elementLenght = element.getText().length() ;
            return elementLenght > 0;

    });
    }
}
