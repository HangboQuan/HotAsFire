package com.alibaba.proxy;

public class UserServiceFactory {

    public static UserService createUserService() {
        UserService target =  UserServiceImpl1.createInstance();
        return new UserServiceProxy(target, "admin");
    }
}
