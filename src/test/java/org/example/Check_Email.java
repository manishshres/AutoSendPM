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

//import java.awt.desktop.SystemSleepEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Check_Email {

    String wrongPass = "Wrong password. Try again or click Forgot password to reset it.";
    String invalidLogin = "Couldn't sign you in";

    String errorMsg = "";

    private final WebDriver driver;

    {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");

        ///Map<String, Object> prefs = new HashMap<String, Object>();

        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        options.addArguments("--disable-infobars");
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("start-maximized");
        options.addArguments("--disable-extensions");
        driver = new FirefoxDriver(options);
    }

    @Given("I am on outlook.com")
    public void i_am_on_outlook_com() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://outlook.live.com/owa/");
        System.out.println("~~~~ Loading outlook.com ~~~~");
    }

    @When("I log into outlook using email:{string} pass:{string}")
    public void i_log_into_outlook_using_email_pass(String myemail, String mypass) throws InterruptedException {
        sleep(2000);
        driver.findElement(By.xpath("//a[@data-task='signin']")).click();
        WebElement emailID = driver.findElement(By.xpath("//input[@id='i0116']"));
        sleep(2000);
        emailID.sendKeys(myemail);
        System.out.println("~~~~ Typing in email address ~~~~");
        WebElement nextButton = driver.findElement(By.xpath("//input[@type='submit']"));
        nextButton.click();
        System.out.println("~~~~ Clicking next button ~~~~");
        sleep(5000);

        List<WebElement> emailError = driver.findElements(By.xpath("//div[@id='usernameError']"));

        if(!driver.findElements(By.xpath("//div[@id='usernameError']")).isEmpty()){

            errorMsg = emailError.get(0).getText();
            return;

        }else if(driver.findElements(By.xpath("//input[@id='i0118']")).isEmpty()) {
            // Other ways to sign in
            driver.findElement(By.xpath("//a[@id='idA_PWD_SwitchToCredPicker']")).click();
            System.out.println("~~~~ Clicking on other ways to sign in  ~~~~");
            sleep(5000);
            driver.findElement(By.xpath("//div[contains(text(),'Use my password')]")).click();
            System.out.println("~~~~ Using password option ~~~~");
            sleep(2000);
        }
        driver.findElement(By.xpath("//input[@id='i0118']")).sendKeys(mypass);
        System.out.println("~~~~ Typing in password ~~~~");
        sleep(5000);

        List<WebElement> passwdError = driver.findElements(By.xpath("//div[@id='passwordError']"));
        if(!passwdError.isEmpty()){
            errorMsg = passwdError.get(0).getText();
            return;
        }else {
            driver.findElement(By.xpath("//input[@value='Sign in']")).click();
            System.out.println("~~~~ Clicking on sign in button ~~~~");
            sleep(5000);
            System.out.println("You need to approve request on Microsoft Authenticator APP");
            System.out.println("~~~~ Waiting for users ~~~~");
            sleep(1000);
        }

    }

    @Then("load from first email from the list")
    public void load_from_first_email_from_the_list() throws InterruptedException {

        sleep(5000);
        driver.findElement(By.xpath("//div[@class='eMnO0knJStwnaHYEFIS0w']")).click();
        System.out.println("~~~~ Selected first email ~~~~");
        sleep(5000);
        System.out.println("~~~~ Time to signout ~~~~");
        sleep(1000);
        driver.get("https://login.live.com/logout.srf");
        sleep(800);
    }

    @Then("I get an error message")
    public void i_get_an_error_message() throws InterruptedException {
        sleep(5000);
        System.out.println("~~~~ You got following error message: ~~~~");
        System.err.println(errorMsg);
        sleep(5000);
    }

    @After
    public void done() {
        driver.quit();
    }


}
