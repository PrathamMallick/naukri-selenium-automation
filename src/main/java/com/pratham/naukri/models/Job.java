package com.pratham.naukri.models;

import java.util.List;

public class Job {

    private String title;
    private String company;
    private String experience;
    private String salary;
    private String location;
    private String description;
    private List<String> skills;
    private String postedDate;
    private String jobUrl;

    public Job(
            String title,
            String company,
            String experience,
            String salary,
            String location,
            String description,
            List<String> skills,
            String postedDate,
            String jobUrl) {

        this.title = title;
        this.company = company;
        this.experience = experience;
        this.salary = salary;
        this.location = location;
        this.description = description;
        this.skills = skills;
        this.postedDate = postedDate;
        this.jobUrl = jobUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getExperience() {
        return experience;
    }

    public String getSalary() {
        return salary;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSkills() {
        return skills;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    @Override
    public String toString() {
        return "\nTitle: " + title +
                "\nCompany: " + company +
                "\nExperience: " + experience +
                "\nSalary: " + salary +
                "\nLocation: " + location +
                "\nDescription: " + description +
                "\nSkills: " + skills +
                "\nPosted: " + postedDate +
                "\nURL: " + jobUrl;
    }
}