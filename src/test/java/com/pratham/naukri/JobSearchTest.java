package com.pratham.naukri;

import java.util.List;

import org.testng.annotations.Test;

import com.pratham.naukri.base.BaseTest;
import com.pratham.naukri.models.Job;
import com.pratham.naukri.pages.JobDetailsPage;
import com.pratham.naukri.pages.JobSearchPage;
import com.pratham.naukri.pages.NaukriHomePage;

public class JobSearchTest extends BaseTest {

    @Test
    public void searchAndExtractJobs() {

        NaukriHomePage homePage = new NaukriHomePage(driver);

        JobSearchPage jobSearchPage = new JobSearchPage(driver);

        homePage.open();

        jobSearchPage.openSearch();

        jobSearchPage.performConfiguredSearch();

        List<Job> jobs = jobSearchPage.getJobs();

        System.out.println("\nTotal jobs found on page: " + jobs.size());

        for (int i = 0; i < jobs.size(); i++) {
            System.out.println("\n========== JOB " + (i + 1) + " ==========");
            System.out.println(jobs.get(i));
        }
    }

    @Test
    public void searchAndViewJobDetails() {

        NaukriHomePage homePage = new NaukriHomePage(driver);

        JobSearchPage jobSearchPage = new JobSearchPage(driver);

        homePage.open();

        jobSearchPage.openSearch();

        jobSearchPage.performConfiguredSearch();

        List<Job> jobs = jobSearchPage.getJobs();

        System.out.println("\nTotal jobs found on page: " + jobs.size());

        for (int i = 0; i < jobs.size(); i++) {
            System.out.println("\n========== JOB " + (i + 1) + " ==========");
            System.out.println(jobs.get(i));
        }

        Job selectedJob = jobs.get(0);

        driver.get(selectedJob.getJobUrl());

        JobDetailsPage detailsPage = new JobDetailsPage(driver);

        System.out.println("\n========== JOB DETAILS ==========");

        System.out.println("Title: " + detailsPage.getJobTitle());

        System.out.println("Company: " + detailsPage.getCompany());

        System.out.println("Experience: " + detailsPage.getExperience());

        System.out.println("Salary: " + detailsPage.getSalary());

        System.out.println("Location: " + detailsPage.getLocation());

        System.out.println("Skills: " + detailsPage.getKeySkills());

        System.out.println("\nDescription:\n" + detailsPage.getDescription());
    }
}