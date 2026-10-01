package com.pratham.naukri.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver createDriver() {

        String browser = ConfigReader.get("browser");

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            String profilePath = ConfigReader.get("chrome.profile.path");

            File profileDirectory = new File(profilePath);

            options.addArguments(
                    "--user-data-dir=" + profileDirectory.getAbsolutePath()
            );

            return new ChromeDriver(options);
        }

        throw new IllegalArgumentException(
                "Unsupported browser: " + browser
        );
    }
}