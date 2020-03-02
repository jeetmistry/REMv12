package com.example.rem.Model;

public class StoreRecruiterProfile {
    String fullname,email,phone,companyname,companylocation,fieldsOfWork;

    public StoreRecruiterProfile(String fullname, String email, String phone, String companyname, String companylocation, String fieldsOfWork) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.companyname = companyname;
        this.companylocation = companylocation;
        this.fieldsOfWork = fieldsOfWork;
    }

    public StoreRecruiterProfile(){

    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getCompanylocation() {
        return companylocation;
    }

    public String getFieldsOfWork() {
        return fieldsOfWork;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setCompanylocation(String companylocation) {
        this.companylocation = companylocation;
    }

    public void setFieldsOfWork(String fieldsOfWork) {
        this.fieldsOfWork = fieldsOfWork;
    }
}
