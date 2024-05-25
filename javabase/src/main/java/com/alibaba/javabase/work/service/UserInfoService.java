package com.alibaba.javabase.work.service;

import com.alibaba.javabase.work.entity.UserInfo;

/**
 * @author quanhangbo
 * @date 2024-05-25 14:44
 */
public interface UserInfoService {

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserInfo getUserInfoDetail(long userId);

    /**
     * 获取用户信息V2
     * @param userId
     * @return
     */
    UserInfo getUserInfoDetailV2(long userId);
}
