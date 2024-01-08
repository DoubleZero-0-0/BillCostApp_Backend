package com.example.backend.controllers;


import com.example.backend.domain.UsersDO;
import com.example.backend.service.UsersService;
import com.example.backend.util.MD5Utils;
import com.example.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public Result registerUser(@RequestBody UsersDO user) {

        //check email it already have database or not
        Boolean emailFound =  usersService.checkEmail(user.getUserEmail());

        if (emailFound) {
            return Result.error("Email Already Have Our Database");
        } else {
            //creating MD5 password
            String MD5_PASSWORD = MD5Utils.string2MD5(user.getUserPassword());
            user.setUserPassword(MD5_PASSWORD);
            usersService.insert_user(user);
            return Result.success();
        }
    }


    @PostMapping("/login")
    public Result loginUser(@RequestBody UsersDO user) {

        //check email,this email have in the database or not
        Boolean emailFound = usersService.checkEmail(user.getUserEmail());

        if (emailFound) {
            //Check the email and password
            String MD5_PASSWORD = MD5Utils.string2MD5(user.getUserPassword());
            UsersDO getUser = usersService.passwordCheck(user.getUserEmail(),MD5_PASSWORD);

            if (getUser != null) {
                //Check Email Are Verified Or Not
                if (getUser.getEmailVerify() != 0)
                {
                    return  Result.success();
                } else {
                    return Result.error("You Email Are Not Verify,Please Verify First");
                }

            } else {
                return Result.error("Credential Are not Match");
            }

        } else {
            return Result.error("This Email Not Found");
        }

    }


}
