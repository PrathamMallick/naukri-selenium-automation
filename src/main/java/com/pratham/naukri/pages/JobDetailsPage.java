package com.pratham.naukri.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JobDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By jobTitle = By.cssSelector("h1.styles_jd-header-title__rZwM1");

    private By company = By.cssSelector("a[title$='Careers']");

    private By experience = By.cssSelector(".styles_jhc__exp__k_giM span");

    private By salary = By.cssSelector(".styles_jhc__salary__jdfEC span");

    private By location = By.cssSelector(".styles_jhc__location__W_pVs");

    private By description = By.cssSelector(".styles_JDC__dang-inner-html__h0K4t");

    private By keySkills = By.cssSelector(".styles_key-skill__GIPn_ " +
                    ".styles_chip__7YCfG span");

    private By applyButton = By.id("apply-button");

    private By saveButton = By.cssSelector("button.styles_save-job-button__WLm_s");

    public JobDetailsPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        );
    }

    public String getJobTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        jobTitle)
        ).getText();
    }

    public String getCompany() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        company)
        ).getText();
    }

    public String getExperience() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        experience)
        ).getText();
    }

    public String getSalary() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        salary)
        ).getText();
    }

    public String getLocation() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        location
                )).getText();
    }

    public String getDescription() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        description)
        ).getText();
    }

    public List<String> getKeySkills() {

        List<WebElement> elements =
                wait.until(ExpectedConditions
                                .presenceOfAllElementsLocatedBy(keySkills));

        List<String> skills =
                new ArrayList<>();

        for (WebElement element : elements) {
            skills.add(element.getText().trim());
        }

        return skills;
    }

    public boolean isApplyButtonVisible() {
        return !driver.findElements(applyButton).isEmpty();
    }

    public boolean isSaveButtonVisible() {
        return !driver.findElements(saveButton).isEmpty();
    }
}