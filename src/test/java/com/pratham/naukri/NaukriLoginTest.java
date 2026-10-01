package com.pratham.naukri;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pratham.naukri.base.BaseTest;
import com.pratham.naukri.pages.NaukriHomePage;

public class NaukriLoginTest extends BaseTest {

    @Test
    public void verifyNaukriLogin() {

        NaukriHomePage homePage = new NaukriHomePage(driver);

        homePage.open();

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        );

        wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.urlContains("/mnjuser"),
                        ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//div[contains(@class,'view-profile')]")
                        )
                )
        );

        System.out.println("Naukri login verified.");
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }
}