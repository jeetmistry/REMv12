package com.example.rem.ui_student.feedback_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FeedbackViewModelStudent extends ViewModel {

    private MutableLiveData<String> mText;

    public FeedbackViewModelStudent() {
        mText = new MutableLiveData<>();
        mText.setValue("This is student feedback fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}