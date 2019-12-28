package com.example.rem.ui_student.myapplications_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyapplicationsViewModelStudent extends ViewModel {

    private MutableLiveData<String> mText;

    public MyapplicationsViewModelStudent() {
        mText = new MutableLiveData<>();
        mText.setValue("This is student My Applications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}