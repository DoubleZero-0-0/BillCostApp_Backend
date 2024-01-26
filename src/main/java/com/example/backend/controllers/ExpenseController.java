package com.example.backend.controllers;

import com.example.backend.service.ExpenseCostService;
import com.example.backend.service.UsersService;
import com.example.backend.util.ApiResponse;
import com.example.backend.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = "Expenses Operations", description = "APIs related to user expense")
public class ExpenseController {

    @Autowired
    private ExpenseCostService expenseCostService;

    @GetMapping("/todayCost")
    @ApiOperation(value = "User Today Cost", notes = " it help to get user today cost")
    public ResponseEntity<Object> todayCost(){
        //get the userid
        Map<String,Object> user = ThreadLocalUtil.get();
        Integer userid = (Integer) user.get("id");
        System.out.println(userid);

        // Get the time of bangladesh
        String desiredTimeZone = "Asia/Dhaka";
        ZonedDateTime utcNow = ZonedDateTime.now();
        ZonedDateTime localTime = utcNow.withZoneSameInstant(java.time.ZoneId.of(desiredTimeZone));
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String BD_Day =  localTime.format(formatter);
        System.out.println(BD_Day);

        //get the cost
        Double TodayCost = expenseCostService.TodayCost(userid,BD_Day);


        return ResponseEntity.ok().body(new ApiResponse("success","Got Today Cost",TodayCost));
    }



    @GetMapping("/weeklyCost")
    @ApiOperation(value = "User Weekly Cost", notes = " it help to get user weekly cost")
    public ResponseEntity<Object> weeklyCost(){
        //get the userid
        Map<String,Object> user = ThreadLocalUtil.get();
        Integer userid = (Integer) user.get("id");
        System.out.println(userid);

        // Get the time of bangladesh
        String desiredTimeZone = "Asia/Dhaka";
        ZonedDateTime utcNow = ZonedDateTime.now();
        ZonedDateTime localTime = utcNow.withZoneSameInstant(java.time.ZoneId.of(desiredTimeZone));
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String BD_Day =  localTime.format(formatter);
        System.out.println(BD_Day);

        //get the cost
        Double weeklyCost = expenseCostService.weeklyCost(userid);


        return ResponseEntity.ok().body(new ApiResponse("success","Got Today Cost",weeklyCost));
    }





}
