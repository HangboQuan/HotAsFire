package com.alibaba.javabase.work.controller;

import com.alibaba.javabase.work.entity.UserInfo;
import com.alibaba.javabase.work.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author quanhangbo
 * @date 2024-05-25 14:48
 */
@Slf4j
@RestController
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping(value = "/hello/userInfo")
    public String helloUserInfo() {
        long userId = 10010101111L;
        UserInfo userInfo = userInfoService.getUserInfoDetail(userId);
        log.info("userInfoDetail:{}", userInfo.toString());
        return userInfo.toString();
    }




}
