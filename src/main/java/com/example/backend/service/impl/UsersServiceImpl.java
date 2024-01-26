package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.UsersDO;
import com.example.backend.service.EmailService;
import com.example.backend.service.UsersService;
import com.example.backend.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
* @author Atul
* @description 针对表【users】的数据库操作Service实现
* @createDate 2024-01-08 11:04:27
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersDO>
    implements UsersService{

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private EmailService emailService;

    @Override
    public Boolean checkEmail(String userEmail) {
        //searching email in database, already have or not
        UsersDO usersDO = usersMapper.selectOne(new QueryWrapper<UsersDO>().eq("user_email",userEmail));

        if (usersDO != null)
        {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void insert_user(UsersDO user) {

        Integer verificationToken = generateRandomToken();
        user.setVerifyToken(verificationToken);
        usersMapper.insert(user);

        //send verification email
//        emailService.sendVerificationEmail(user.getUserEmail(), verificationToken);

    }

    @Override
    public UsersDO passwordCheck(String userEmail, String md5Password) {
        UsersDO user = usersMapper.selectOne(new QueryWrapper<UsersDO>().eq("user_email",userEmail)
                .eq("user_password",md5Password));

        return user;
    }

    @Override
    public Boolean emailVerified(String userEmail, Integer verifyToken) {

       UsersDO user = usersMapper.selectOne(new QueryWrapper<UsersDO>()
                .eq("user_email",userEmail)
                .eq("verify_token",verifyToken));

       if (user != null) {
           user.setEmailVerify(1);
           usersMapper.updateById(user);
           return true;
       } else {
           return false;
       }

    }

    @Override
    public UsersDO userInfo(Integer userid) {
        return  usersMapper.selectById(userid) ;
    }


    private Integer generateRandomToken() {
        Random random = new Random();
        int token = 1000 + random.nextInt(9000);

        return token;
    }
}




