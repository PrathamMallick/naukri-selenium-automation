package com.pratham.naukri.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pratham.naukri.utils.ConfigReader;

public class NaukriProfilePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String PROFILE_URL = "https://www.naukri.com/mnjuser/profile";

    // Locators
    private By updateResumeButton = By.xpath("//button[normalize-space()='Update resume']");

    private By resumeFileInput = By.cssSelector("input[type='file']");

    // private By saveButton = By.xpath("//button[normalize-space()='Save']");

    public NaukriProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void openProfile() {
        driver.get(PROFILE_URL);
    }

    public boolean isResumeUploadedToday() {

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                        By.tagName("body"),
                        "Uploaded on"));

        String pageText = driver.findElement(By.tagName("body")).getText();

        Pattern pattern = Pattern.compile("Uploaded on\\s+([A-Za-z]{3}\\s+\\d{1,2},\\s+\\d{4})");

        Matcher matcher = pattern.matcher(pageText);

        if (!matcher.find()) {
            throw new RuntimeException(
                    "Could not find 'Uploaded on' date on profile");
        }

        String uploadedDate = matcher.group(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "MMM d, yyyy",
                Locale.ENGLISH);

        LocalDate resumeDate = LocalDate.parse(uploadedDate, formatter);

        return resumeDate.equals(LocalDate.now());
    }

    public void uploadResume() {

        String resumePath = ConfigReader.get("resume.path");

        File resumeFile = new File(resumePath);

        if (!resumeFile.exists()) {
            throw new RuntimeException("Resume file not found: " + resumeFile.getAbsolutePath());
        }

        wait.until(ExpectedConditions.elementToBeClickable(updateResumeButton)).click();

        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(resumeFileInput));

        fileInput.sendKeys(resumeFile.getAbsolutePath());
    }

    public void verifyResumeUpload() {

        driver.navigate().refresh();

        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.tagName("body"),
                        "Uploaded on"));

        if (!isResumeUploadedToday()) {
            throw new AssertionError("Resume upload verification failed. " + "Resume was not uploaded today.");
        }

        System.out.println("Resume upload verified successfully. " + "Resume was uploaded today.");
    }
}