package com.pratham.naukri;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pratham.naukri.utils.DriverFactory;

public class NaukriLoginTest {

    @Test
    public void loginToNaukri() {

        WebDriver driver = DriverFactory.createDriver();
        driver.manage().window().maximize();

        try {
            driver.get("https://www.naukri.com/");

            WebDriverWait wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(120)
            );

            System.out.println("Naukri opened.");
            System.out.println("Please log in manually if required.");

            wait.until(
                    ExpectedConditions.or(
                            ExpectedConditions.urlContains("/mnjuser"),
                            ExpectedConditions.presenceOfElementLocated(
                                    By.xpath("//div[contains(@class,'view-profile')]")
                            )
                    )
            );

            System.out.println("Login successful!");
            System.out.println("Current URL: " + driver.getCurrentUrl());

        } finally {
            driver.quit();
        }
    }
}