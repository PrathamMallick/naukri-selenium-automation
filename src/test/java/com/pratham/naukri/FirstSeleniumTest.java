package com.pratham.naukri;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstSeleniumTest {

    @Test 
    public void openGoogle() {
        
        //Start Chrome Browser
        WebDriver driver = new ChromeDriver();

        //Open Google.com
        driver.get("https://www.google.com");

        System.out.println("Title of the page is: " + driver.getTitle());

        driver.quit();
    }

}
