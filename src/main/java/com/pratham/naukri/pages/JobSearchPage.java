package com.pratham.naukri.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pratham.naukri.models.Job;
import com.pratham.naukri.utils.ConfigReader;

public class JobSearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Homepage search
    private By openSearchButton = By.cssSelector("button[aria-label='Search jobs here']");

    private By jobTypeInput = By.id("jobType");

    private By keywordInput = By.cssSelector("input[aria-label='Enter keyword, designation, or companies']");

    private By locationInput = By.cssSelector("input[aria-label='Enter location']");

    private By searchButton = By.xpath("//span[normalize-space()='Search']");

    // Job type options
    private By jobOption = By.cssSelector("li[value='ajob']");

    private By internshipOption = By.cssSelector("li[value='ainternship']");

    // Search results
    private By jobCards = By.cssSelector("div.cust-job-tuple");

    public JobSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30));
    }

    public void openSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(openSearchButton)).click();
    }

    public void performConfiguredSearch() {

        String jobType = ConfigReader.get("job.type");

        String keywords = ConfigReader.get("job.keywords");

        String locations = ConfigReader.get("job.locations");

        selectJobType(jobType);
        enterKeywords(keywords);
        enterLocation(locations);
        search();
    }

    public void selectJobType(String type) {

        wait.until(ExpectedConditions.elementToBeClickable(jobTypeInput)).click();

        if (type.equalsIgnoreCase("job")) {

            wait.until(ExpectedConditions.elementToBeClickable(jobOption)).click();

        } else if (type.equalsIgnoreCase("internship")) {

            wait.until(ExpectedConditions.elementToBeClickable(internshipOption)).click();

        } else {
            throw new IllegalArgumentException("Unsupported job type: " + type);
        }
    }

    public void enterKeywords(String keywords) {

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(keywordInput));

        input.clear();
        input.sendKeys(keywords);
    }

    public void enterLocation(String location) {

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));

        input.clear();
        input.sendKeys(location);
    }

    public void search() {

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(jobCards));
    }

    public List<Job> getJobs() {

        List<WebElement> cards = driver.findElements(jobCards);

        List<Job> jobs = new ArrayList<>();

        for (WebElement card : cards) {

            List<WebElement> titleElements = card.findElements(By.cssSelector("a.title"));

            if (titleElements.isEmpty()) {
                continue;
            }

            String title = titleElements.get(0).getText();

            String company = getOptionalText(
                    card,
                    By.cssSelector("a.comp-name"),
                    "Not specified");

            String experience = getOptionalText(
                    card,
                    By.cssSelector(".expwdth"),
                    "Not specified");

            String salary = getOptionalText(
                    card,
                    By.cssSelector(".sal-wrap span[title]"),
                    "Not specified");

            String location = getOptionalText(
                    card,
                    By.cssSelector(".locWdth"),
                    "Not specified");

            String description = getOptionalText(
                    card,
                    By.cssSelector(".job-desc"),
                    "Not specified");

            String postedDate = getOptionalText(
                    card,
                    By.cssSelector(".job-post-day"),
                    "Not specified");

            String jobUrl = titleElements.get(0).getAttribute("href");

            List<String> skills = new ArrayList<>();

            List<WebElement> skillElements = card.findElements(
                    By.cssSelector(".tags-gt .tag-li"));

            for (WebElement skill : skillElements) {
                skills.add(skill.getText());
            }

            Job job = new Job(
                    title,
                    company,
                    experience,
                    salary,
                    location,
                    description,
                    skills,
                    postedDate,
                    jobUrl);

            jobs.add(job);
        }

        return jobs;
    }

    private String getOptionalText(
            WebElement parent,
            By locator,
            String defaultValue) {

        List<WebElement> elements = parent.findElements(locator);

        if (elements.isEmpty()) {
            return defaultValue;
        }

        return elements.get(0).getText().trim();
    }
}