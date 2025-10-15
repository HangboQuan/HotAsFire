package com.alibaba.proxy;

public class UserServiceImpl1 implements UserService{

    // 构造器私有，防止外部直接new
    private UserServiceImpl1() {}

    static UserServiceImpl1 createInstance() {
        return new UserServiceImpl1();
    }

    @Override
    public void deleteUser(String userId) {
        System.out.println("删除用户：" + userId);
    }
}
