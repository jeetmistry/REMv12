package com.example.rem.ui_student.share_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModelStudent extends ViewModel {

    private MutableLiveData<String> mText;

    public ShareViewModelStudent() {
        mText = new MutableLiveData<>();
        mText.setValue("This is student share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}