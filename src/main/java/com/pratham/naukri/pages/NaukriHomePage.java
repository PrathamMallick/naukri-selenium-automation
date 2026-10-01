package com.pratham.naukri.pages;

import org.openqa.selenium.WebDriver;

public class NaukriHomePage {

    private WebDriver driver;

    public NaukriHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.naukri.com/");
    }
}