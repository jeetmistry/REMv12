package com.example.rem.ui_student.logout_student;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogoutViewModelStudent extends ViewModel {

    private MutableLiveData<String> mText;

    public LogoutViewModelStudent() {
        mText = new MutableLiveData<>();
        mText.setValue("This is student logout fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}