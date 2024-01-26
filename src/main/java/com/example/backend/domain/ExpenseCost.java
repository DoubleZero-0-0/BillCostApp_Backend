package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName expense_cost
 */
@TableName(value ="expense_cost")
@Data
public class ExpenseCost implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer expenseCostId;

    /**
     * 
     */
    private Integer categoriesId;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private BigDecimal expenseCost;

    /**
     * 
     */
    private Date dateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}