package com.pratham.naukri;

import java.util.List;

import org.testng.annotations.Test;

import com.pratham.naukri.base.BaseTest;
import com.pratham.naukri.models.Job;
import com.pratham.naukri.pages.JobSearchPage;
import com.pratham.naukri.pages.NaukriHomePage;
import com.pratham.naukri.utils.ConfigReader;

public class JobSearchTest extends BaseTest {

    @Test
    public void searchAndExtractJobs() {

        NaukriHomePage homePage = new NaukriHomePage(driver);

        JobSearchPage jobSearchPage = new JobSearchPage(driver);

        homePage.open();

        jobSearchPage.openSearch();

        String jobType = ConfigReader.get("job.type");
        String keywords = ConfigReader.get("job.keywords");
        String locations = ConfigReader.get("job.locations");

        jobSearchPage.selectJobType(jobType);
        jobSearchPage.enterKeywords(keywords);
        jobSearchPage.enterLocation(locations);

        jobSearchPage.search();

        List<Job> jobs = jobSearchPage.getJobs();

        System.out.println("\nTotal jobs found on page: " + jobs.size());

        for (int i = 0; i < jobs.size(); i++) {

            System.out.println("\n========== JOB " + (i + 1) + " ==========");
            System.out.println(jobs.get(i));
        }
    }
}