package com.example.rem.ui_recruiter.viewapplications_recruiter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewapplicationsViewModelRecruiter extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewapplicationsViewModelRecruiter() {
        mText = new MutableLiveData<>();
        mText.setValue("This is recruiter View Applications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}