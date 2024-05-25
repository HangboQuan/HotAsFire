package com.alibaba.javabase.work.service.impl;

import com.alibaba.javabase.work.annotation.ServiceRedirect;
import com.alibaba.javabase.work.entity.UserInfo;
import com.alibaba.javabase.work.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author quanhangbo
 * @date 2024-05-25 14:49
 */
@Service
public class UseInfoServiceImpl implements UserInfoService {

    @Override
    @ServiceRedirect(serviceImplClazz = UseInfoServiceImpl.class, methodName = "getUserInfoDetailV2", paramsClazz = {long.class}, redirectPath = "$")
    public UserInfo getUserInfoDetail(long userId) {
        // 模拟userinfo的数据

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId).setUserName("quanhangbo").setNickName("Tom").setBirthday(19991229).setAddress("Beijing");
        return userInfo;
    }

    @Override
    public UserInfo getUserInfoDetailV2(long userId) {

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId).setUserName("jinbohang").setNickName("Jerry").setBirthday(19991122).setAddress("Shanghai");
        return userInfo;
    }
}
