package com.example.rem.Model;

public class ViewJobsStudent {
    public String companyname, companydescription,jobpost,workingtype;

    public ViewJobsStudent() {

    }

    public ViewJobsStudent(String companyname, String companydescription, String jobpost, String workingtype) {
        this.companyname = companyname;
        this.companydescription = companydescription;
        this.jobpost = jobpost;
        this.workingtype = workingtype;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanydescription() {
        return companydescription;
    }

    public void setCompanydescription(String companydescription) {
        this.companydescription = companydescription;
    }

    public String getJobpost() {
        return jobpost;
    }

    public void setJobpost(String jobpost) {
        this.jobpost = jobpost;
    }

    public String getWorkingtype() {
        return workingtype;
    }

    public void setWorkingtype(String workingtype) {
        this.workingtype = workingtype;
    }
}
