package com.example.rem.ui_admin.help_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HelpViewModelAdmin extends ViewModel {

    private MutableLiveData<String> mText;

    public HelpViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("Help for admin\n\n" +
                "1.Admin can login into the page by providing valid credentials.\n\n" +
                "2.After you login as admin and the home page appears you can slide in from left side moving towards right or simply click on the toggle button in the upper left corner to get more options and navigate through other functionalities.\n\n" +
                "3.In the admin the admin can view the students who applied for the job.\n\n" +
                "4.The admin can also analyse the companies that posted their jobs.\n\n" +
                "5.Admin will be able to see the feedbacks from the user.\n\n" +
                "6.If the admin desires to go settings of the mobile he/she can directly navigate to the mobile settings by clicking the three dots at the top right corner instead of following the long procedure of closing app and starting the settings.\n\n");
    }

    public LiveData<String> getText() {
        return mText;
    }
}