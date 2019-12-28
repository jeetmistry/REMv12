package com.example.rem.ui_student.profile_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModelStudent extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfileViewModelStudent() {
        mText = new MutableLiveData<>();
        mText.setValue("This is student profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}