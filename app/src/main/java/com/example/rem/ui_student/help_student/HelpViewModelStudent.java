package com.example.rem.ui_student.help_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HelpViewModelStudent extends ViewModel {

    private MutableLiveData<String> mText;

    public HelpViewModelStudent() {
        mText = new MutableLiveData<>();
        mText.setValue("This is student help fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}