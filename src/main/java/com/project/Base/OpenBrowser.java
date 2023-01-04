package com.project.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OpenBrowser {

    public static  WebDriver driver;

    public static WebDriver openBrowser(String browserName)
    {
        switch (browserName)
        {
            case "chrome" : driver = new ChromeDriver();
                break;
            case "fireFox" : driver = new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        return driver;
    }

}
