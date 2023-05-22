package com.example.englishlearning.Controller;

import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.enity.User;
import com.example.englishlearning.Status;

public class UserController {
    private DataBaseController dataBaseController;
    public UserController(DataBaseController dbc){
        dataBaseController=dbc;
    }
    public Boolean login(User user){
        String username=user.getUsername();
        String password=user.getPassword();
        switch (dataBaseController.loginUser(user)){
            case Status.SUCCESS:
                return true;
            default:
                return false;

        }

    }
}
