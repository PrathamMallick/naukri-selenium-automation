package com.pratham.naukri;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.pratham.naukri.utils.DriverFactory;

public class FirstSeleniumTest {

    @Test 
    public void openGoogle() {
        
        //Start Chrome Browser
        WebDriver driver = DriverFactory.createDriver();

        //Open Google.com
        driver.get("https://www.google.com");

        System.out.println("Title of the page is: " + driver.getTitle());
        //Thread.sleep(5000); // Wait for 5 seconds to see the result

        driver.quit();
    }

}
