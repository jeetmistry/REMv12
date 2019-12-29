package com.example.rem.ui_recruiter.help_recruiter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HelpViewModelRecruiter extends ViewModel {

    private MutableLiveData<String> mText;

    public HelpViewModelRecruiter() {
        mText = new MutableLiveData<>();
        mText.setValue("This is recruiter Help fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}