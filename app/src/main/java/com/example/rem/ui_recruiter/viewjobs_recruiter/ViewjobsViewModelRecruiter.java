package com.example.rem.ui_recruiter.viewjobs_recruiter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewjobsViewModelRecruiter extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewjobsViewModelRecruiter() {
        mText = new MutableLiveData<>();
        mText.setValue("This is recruiter View Jobs fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}