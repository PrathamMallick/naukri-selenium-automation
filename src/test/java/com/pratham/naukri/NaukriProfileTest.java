package com.pratham.naukri;

import org.testng.annotations.Test;

import com.pratham.naukri.base.BaseTest;
import com.pratham.naukri.pages.NaukriProfilePage;

public class NaukriProfileTest extends BaseTest {

    @Test
    public void updateResumeIfRequired() {

        NaukriProfilePage profilePage = new NaukriProfilePage(driver);

        profilePage.openProfile();

        if (profilePage.isResumeUploadedToday()) {

            System.out.println("Resume is already uploaded today. Skipping upload.");

        } else {

            System.out.println("Resume was not uploaded today. Uploading resume...");

            profilePage.uploadResume();

            profilePage.verifyResumeUpload();
        }
    }
}