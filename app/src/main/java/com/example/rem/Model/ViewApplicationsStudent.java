package com.example.rem.Model;

public class ViewApplicationsStudent {
    String jobpost,companyname,companydescription,workingtype,status;

    public ViewApplicationsStudent() {
    }

    public ViewApplicationsStudent(String jobpost, String companyname, String companydescription, String workingtype, String status) {
        this.jobpost = jobpost;
        this.companyname = companyname;
        this.companydescription = companydescription;
        this.workingtype = workingtype;
        this.status = status;
    }

    public String getJobpost() {
        return jobpost;
    }

    public void setJobpost(String jobpost) {
        this.jobpost = jobpost;
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

    public String getWorkingtype() {
        return workingtype;
    }

    public void setWorkingtype(String workingtype) {
        this.workingtype = workingtype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
