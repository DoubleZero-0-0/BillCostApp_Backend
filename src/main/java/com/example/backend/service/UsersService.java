package com.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.domain.UsersDO;

/**
* @author Atul
* @description 针对表【users】的数据库操作Service
* @createDate 2024-01-08 11:04:27
*/
public interface UsersService extends IService<UsersDO> {

    Boolean checkEmail(String userEmail);

    void insert_user(UsersDO user);


    UsersDO passwordCheck(String userEmail, String md5Password);
}
