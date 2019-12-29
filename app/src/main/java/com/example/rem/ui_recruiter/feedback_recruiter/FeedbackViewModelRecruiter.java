package com.example.rem.ui_recruiter.feedback_recruiter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FeedbackViewModelRecruiter extends ViewModel {

    private MutableLiveData<String> mText;

    public FeedbackViewModelRecruiter() {
        mText = new MutableLiveData<>();
        mText.setValue("This is recruiter feedback fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}