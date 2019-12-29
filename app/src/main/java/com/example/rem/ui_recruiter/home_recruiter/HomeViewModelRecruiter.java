package com.example.rem.ui_recruiter.home_recruiter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModelRecruiter extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModelRecruiter() {
        mText = new MutableLiveData<>();
        mText.setValue("This is recruiter home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}