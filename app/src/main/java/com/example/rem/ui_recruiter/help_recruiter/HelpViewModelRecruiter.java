package com.example.rem.ui_recruiter.help_recruiter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HelpViewModelRecruiter extends ViewModel {

    private MutableLiveData<String> mText;

    public HelpViewModelRecruiter() {
        mText = new MutableLiveData<>();
        mText.setValue("Help for Recruiter\n" +
                "1.Recruiter can login to the page by providing id and password on different devices.\n" +
                "2.Once you login as Recruiter , you did not need to login everytime in student unless you logout from module. \n" +
                "3.After you login as Recruiter and the home page appears you can slide in from left side moving towards right or simply click on the toggle button in the upper left corner to get more options and navigate through other functionalities.\n" +
                "4.The Recruiter has different functionalities like company can view jobs that they previously applied for students to enrol for, view notifications, add jobs that where there is vacancy ,and can view who has enrolled for the job in the view applications.\n" +
                "5.The Recruiter has to first fill the profile and submit it. Company or recruiter will be able to see whether the information filled in the profile is correct or not in the home page and update the profile if anything is incorrect or in order to change something.\n" +
                "6.The Recruiter is able to write and submit the feedbacks and also see the previous feedback of the user. \n" +
                "7.If the Recruiter desires to go settings of the mobile he/she can directly navigate to the mobile settings by clicking the three dots at the top right corner instead of following the long procedure of closing app and starting the settings.\n");
    }

    public LiveData<String> getText() {
        return mText;
    }
}