package com.example.rem.ui_admin.profile_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModelAdmin extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfileViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Admin Profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}