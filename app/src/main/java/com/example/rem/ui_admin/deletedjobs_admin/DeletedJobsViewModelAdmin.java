package com.example.rem.ui_admin.deletedjobs_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeletedJobsViewModelAdmin extends ViewModel {

    private MutableLiveData<String> mText;

    public DeletedJobsViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Admin Home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}