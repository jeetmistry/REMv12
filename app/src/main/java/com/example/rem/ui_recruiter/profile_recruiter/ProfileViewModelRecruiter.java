package com.example.rem.ui_recruiter.profile_recruiter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModelRecruiter extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfileViewModelRecruiter() {
        mText = new MutableLiveData<>();
        mText.setValue("This is recruiter Profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}