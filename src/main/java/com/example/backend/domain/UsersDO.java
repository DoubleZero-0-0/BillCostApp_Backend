package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName users
 */
@TableName(value ="users")
@Data
public class UsersDO implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 
     */
    private String userName;

    /**
     * 
     */
    private String userEmail;

    /**
     * 
     */
    private String userPassword;

    /**
     * 
     */
    private Integer emailVerify;

    /**
     * 
     */
    private Integer verifyToken;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}