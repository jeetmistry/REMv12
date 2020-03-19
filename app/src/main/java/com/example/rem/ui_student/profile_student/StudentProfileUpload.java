package com.example.rem.ui_student.profile_student;

public class StudentProfileUpload {
    private String mImageUrl;
    private String mKey;
    public StudentProfileUpload(){
        //empty constructor
    }

    public StudentProfileUpload(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public StudentProfileUpload(String mImageUrl, String mKey) {

        this.mImageUrl = mImageUrl;
        this.mKey = mKey;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}
