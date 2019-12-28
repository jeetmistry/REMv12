package com.example.rem.ui_student.jobs_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JobsViewModelStudent extends ViewModel {

    private MutableLiveData<String> mText;

    public JobsViewModelStudent() {
        mText = new MutableLiveData<>();
        mText.setValue("This is student jobs fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}