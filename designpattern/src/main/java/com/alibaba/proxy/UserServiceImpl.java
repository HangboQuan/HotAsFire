package com.alibaba.proxy;

public class UserServiceImpl implements UserService {

    @Override
    public void deleteUser(String userId) {
        System.out.println("删除用户：" + userId);
    }
}
