package com.example.rem.ui_student.help_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HelpViewModelStudent extends ViewModel {

    private MutableLiveData<String> mText;

    public HelpViewModelStudent() {
        mText = new MutableLiveData<>();
        mText.setValue("Help for student\n\n" +
                "1.Student can login to the page by providing id and password on different devices.\n\n" +
                "2.Once you login as student , you did not need to login everytime in student unless you logout from module. \n\n" +
                "3.After you login as student and the home page appears you can slide in from left side moving towards right or simply click on the toggle button in the upper left corner to get more options and navigate through other functionalities.\n\n" +
                "4.The student has different functionalities like he/she can view company details, view notifications, search for the jobs that fit in his/her category ,see the available jobs and view the jobs that he/she applied for.\n\n" +
                "5.The student has to first fill the profile and submit it. He/She will be able to see whether the information filled in the profile is correct or not in the home page and update the profile if anything is incorrect or in order to change something.\n\n" +
                "6.The student is able to write and submit the feedbacks and also see the previous feedback of the user. \n\n" +
                "7.If the student desires to go settings of the mobile he/she can directly navigate to the mobile settings by clicking the three dots at the top right corner instead of following the long procedure of closing app and starting the settings.\n\n");
    }

    public LiveData<String> getText() {
        return mText;
    }
}