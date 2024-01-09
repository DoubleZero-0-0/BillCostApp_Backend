package com.example.backend.controllers;


import com.example.backend.domain.UsersDO;
import com.example.backend.service.UsersService;
import com.example.backend.util.ApiResponse;
import com.example.backend.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = "User Operations", description = "APIs related to user management")
public class UsersController {

    @Autowired
    private UsersService usersService;


    @PostMapping("/register")
    @ApiOperation(value = "User Registration", notes = "Register a new user with the provided user details.")
    public ResponseEntity<Object> registerUser(@RequestBody UsersDO user) {

        //check email it already have database or not
        Boolean emailFound =  usersService.checkEmail(user.getUserEmail());

        if (emailFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("error","this email already have in our database",null));
        } else {
            //creating MD5 password
            String MD5_PASSWORD = MD5Utils.string2MD5(user.getUserPassword());
            user.setUserPassword(MD5_PASSWORD);
            usersService.insert_user(user);
            return ResponseEntity.ok().body(new ApiResponse("success","Registration Successfully",null));
        }
    }


    @PostMapping("/login")
    @ApiOperation( value = "User Login", notes = "Authenticate and log in a user with the provided credentials.")
    public ResponseEntity<Object> loginUser(@RequestBody UsersDO user) {

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
                    return  ResponseEntity.ok().body(new ApiResponse("success","Login Successfully",null));
                } else {
                    Map<String,Object> usermap = new HashMap<>();
                    usermap.put("Email",getUser.getUserEmail());
                    usermap.put("Name",getUser.getUserName());
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiResponse("406","email not verified",usermap));
                }

            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiResponse("error","credential are not match",null));
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("error","this email not found",null));
        }

    }


    @ApiOperation( value = "Email Verify",notes = "Initiate email verification by sending a token to the user's email address " +
            "The user should provide the received token for verification.")
    @PostMapping("/emailVerified")
    public ResponseEntity<Object> emailVerification(@RequestBody UsersDO userVerification)
    {
        Boolean emailCheck = usersService.checkEmail(userVerification.getUserEmail());

        if (emailCheck) {
           Boolean emailVerified = usersService.emailVerified(userVerification.getUserEmail(),userVerification.getVerifyToken());
           if (emailVerified) {
               return ResponseEntity.ok().body(new ApiResponse("success","verification successfully!!",null));
           } else {
               return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiResponse("error","verification code are wrong",null));
           }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiResponse("error","email not found",null));
        }
    }


}
