package com.example.rem.ui_student.send_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SendViewModelStudent extends ViewModel {

    private MutableLiveData<String> mText;

    public SendViewModelStudent() {
        mText = new MutableLiveData<>();
        mText.setValue("This is student send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}