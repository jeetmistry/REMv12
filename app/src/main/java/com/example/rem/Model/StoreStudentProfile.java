package com.example.rem.Model;

public class StoreStudentProfile {
    String name,email,phone,city,qualification,collegeName,passingYear;
    String fields;

    public StoreStudentProfile(String name, String email, String phone, String city, String qualification, String collegeName, String passingYear, String fields) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.qualification = qualification;
        this.collegeName = collegeName;
        this.passingYear = passingYear;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getQualification() {
        return qualification;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public String getPassingYear() {
        return passingYear;
    }

    public String getFields() {
        return fields;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
}
