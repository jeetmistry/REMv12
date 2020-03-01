package com.example.rem.Model;

public class StoreRecruiterProfile {
    String fullname,email,phone,companyname,companylocation;

    public StoreRecruiterProfile(String fullname, String email, String phone, String companyname, String companylocation) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.companyname = companyname;
        this.companylocation = companylocation;
    }

    public StoreRecruiterProfile(){

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanylocation() {
        return companylocation;
    }

    public void setCompanylocation(String companylocation) {
        this.companylocation = companylocation;
    }
}
