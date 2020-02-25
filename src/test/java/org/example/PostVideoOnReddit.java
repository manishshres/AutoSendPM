package org.example;

import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import static java.lang.Thread.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class PostVideoOnReddit {
    String youtubeBase = "https://youtube.com/watch?v=";

    private final WebDriver driver; {
        System. setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");

        Map<String, Object> prefs = new HashMap<String, Object>();

        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        options.addArguments("--disable-infobars");
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("start-maximized");
        options.addArguments("--disable-extensions");
        driver = new FirefoxDriver(options);
    }

    @Given("I am on google.com")
    public void i_am_on_google_com() {
        // Write code here that turns the phrase above into concrete actionsApprove request on Microsoft Authenticator APP
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https:\\www.google.com");
    }

    @When("I search for {string} and login using user:{string} pass:{string}")
    public void i_search_for_and_login_using_user_pass(String query, String myusrname, String mypasswd) throws InterruptedException {
        WebElement serach = driver.findElement(By.name("q"));
        serach.sendKeys(query);
        serach.submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> searchResults = driver.findElements(By.xpath("//h3[@class='LC20lb DKV0Md']"));
        searchResults.get(0).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.id("loginUsername")).sendKeys(myusrname);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.id("loginPassword")).sendKeys(mypasswd);
        sleep(2000);

        driver.findElement(By.className("AnimatedForm__submitButton")).click();
        sleep(5000);

    }

    @Then("post video with title:{string} and video WatchID:{string}")
    public void post_video_with_title_and_video_link(String postTitle, String watchID) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        driver.navigate().refresh();
        sleep(5000);

        driver.navigate().to("https://www.reddit.com/r/test/submit");
        sleep(5000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[contains(.,'Link')]")).click();
        sleep(500);

        driver.findElement(By.xpath("//textarea[@placeholder='Title']")).sendKeys(postTitle);
        driver.findElement(By.xpath("//textarea[@placeholder='Url']")).sendKeys(youtubeBase + watchID);
        sleep(2000);
        driver.findElement(By.xpath("//button[@class='_2JBsHFobuapzGwpHQjrDlD _18Bo5Wuo3tMV-RDB8-kh8Z ']")).click();
        sleep(5000);
    }

    @After
    public void done(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.quit();
    }

}
