package com.example.rem.ui_admin.viewstudents_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewstudentsViewModelAdmin extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewstudentsViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Admin View Students fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}