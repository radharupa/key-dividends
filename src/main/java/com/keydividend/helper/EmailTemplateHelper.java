package com.keydividend.helper;

import com.keydividend.entity.User;

public class EmailTemplateHelper {

    public String userSignUpTemplate(User user)
    {
        String text = "";

        text = "Hi " +  user.getFirstName() + " " + user.getLastName();
        text = "</br></br>";
        text = "Thanks for registering with Us. Please click on the below link to activate your account";
        text = "</br></br>";
        text = "<a href=\"http://127.0.0.1:8080/v1/user/activateUser?userId=\"" + user.getUserId() +">Activate</a>";


        return text;
    }
}
