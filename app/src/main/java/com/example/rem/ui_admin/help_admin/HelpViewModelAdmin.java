package com.example.rem.ui_admin.help_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HelpViewModelAdmin extends ViewModel {

    private MutableLiveData<String> mText;

    public HelpViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Admin Help fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}