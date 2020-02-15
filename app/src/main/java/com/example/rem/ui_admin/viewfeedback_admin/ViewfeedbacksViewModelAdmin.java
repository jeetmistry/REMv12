package com.example.rem.ui_admin.viewfeedback_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewfeedbacksViewModelAdmin extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewfeedbacksViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Admin View Feedbacks fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}