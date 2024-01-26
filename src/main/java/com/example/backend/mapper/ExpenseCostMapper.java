package com.example.backend.mapper;

import com.example.backend.domain.ExpenseCost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Atul
* @description 针对表【expense_cost】的数据库操作Mapper
* @createDate 2024-01-26 15:31:19
* @Entity com.example.backend.domain.ExpenseCost
*/
public interface ExpenseCostMapper extends BaseMapper<ExpenseCost> {

    Double TodayCost(Integer userid,String bdDay);

    Double weeklyCost(Integer userid);
}




