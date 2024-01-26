package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.ExpenseCost;
import com.example.backend.service.ExpenseCostService;
import com.example.backend.mapper.ExpenseCostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Atul
* @description 针对表【expense_cost】的数据库操作Service实现
* @createDate 2024-01-26 15:31:19
*/
@Service
public class ExpenseCostServiceImpl extends ServiceImpl<ExpenseCostMapper, ExpenseCost>
    implements ExpenseCostService{

    @Autowired
    private ExpenseCostMapper expenseCostMapper;

    @Override
    public Double TodayCost(Integer userid,String bdDay) {
        return expenseCostMapper.TodayCost(userid,bdDay) ;
    }

    @Override
    public Double weeklyCost(Integer userid) {
        return expenseCostMapper.weeklyCost(userid);
    }
}




