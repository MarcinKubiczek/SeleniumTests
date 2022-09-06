package task;

import framework.utils.DTO.YouTubeVideoDTO;
import framework.utils.DriverFactory;
import framework.utils.page.YoutubePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class Task4Test {

    @Test
    public void Task4Test(){
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();
        YoutubePage youtubePage = new YoutubePage(webDriver);
        youtubePage.openYoutubePage();
        youtubePage.acceptCookies();
        //Lista kafelkow
        List<YouTubeVideoDTO> ytTileList = new ArrayList<YouTubeVideoDTO>();
        youtubePage.assignList(12,ytTileList);
        youtubePage.printNameAndNotLiveTitles(ytTileList);
        webDriver.quit();
        
    }
}
