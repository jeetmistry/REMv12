package com.example.rem.ui_recruiter.addjobs_recruiter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddjobsViewModelRecruiter extends ViewModel {

    private MutableLiveData<String> mText;

    public AddjobsViewModelRecruiter() {
        mText = new MutableLiveData<>();
        mText.setValue("This is recruiter Add Jobs fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}