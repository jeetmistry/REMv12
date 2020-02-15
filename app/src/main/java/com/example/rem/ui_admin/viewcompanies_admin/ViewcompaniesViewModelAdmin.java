package com.example.rem.ui_admin.viewcompanies_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewcompaniesViewModelAdmin extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewcompaniesViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Admin View Companies fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}