package com.sombrainc.memsourcebot;

import com.google.common.base.Strings;
import org.apache.commons.cli.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class SeleniumBotApplication {

    public static void main(String[] args) throws ParseException {
        PropertyUtils.load(args);

        // open chrome driver
        WebDriver driver = initializeChromeDriver();

//        // login
        login(driver);
//
        while (true) {
            //  accept jobs if button is clickable
            acceptJobIfClickable(driver);
            // refresh page
            driver.navigate().refresh();
        }
    }

    private static void acceptJobIfClickable(WebDriver driver) {
        //
        final Select selectFilter = new Select(driver.findElement(By.name("selectFilter")));
        selectFilter.selectByVisibleText("New work");

        final WebElement jobSelectionToggle = driver.findElement(By.name("jobSelectionToggle"));
        jobSelectionToggle.click();

        final WebElement _action_acceptJobs = driver.findElement(By.name("_action_acceptJobs"));
        final String disabled = _action_acceptJobs.getAttribute("disabled");

        if (Strings.isNullOrEmpty(disabled) || !disabled.equalsIgnoreCase("true")) {
            _action_acceptJobs.click();
            System.out.println("JOB HAS BEEN FOUND");
            driver.close();
        }
    }

    private static WebDriver initializeChromeDriver() {
        final String path = PropertyUtils.getChromeDriverPath();
        final File file = new File(path);
        if (!file.exists()) {
            throw new NullPointerException("Chrome web driver doesn't exist by path: " + path);
        }
        System.setProperty("webdriver.chrome.driver", file.getPath());

        // Initialize browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static void login(WebDriver driver) {
        // Open Google
        driver.get("https://cloud.mem" + "source.com/web/login" + "/auth?format_=");

        final WebElement username = driver.findElement(By.name("username"));
        username.sendKeys(PropertyUtils.getUsername());

        final WebElement password = driver.findElement(By.name("password"));
        password.sendKeys(PropertyUtils.getPassword());

        final WebElement submit = driver.findElement(By.name("submit"));
        submit.click();
    }

}
