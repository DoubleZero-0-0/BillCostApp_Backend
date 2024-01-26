package com.example.backend.service;

import com.example.backend.domain.ExpenseCost;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Atul
* @description 针对表【expense_cost】的数据库操作Service
* @createDate 2024-01-26 15:31:19
*/
public interface ExpenseCostService extends IService<ExpenseCost> {

    Double TodayCost(Integer userid,String bdDay);

    Double weeklyCost(Integer userid);
}
