package com.alibaba.proxy;

public class UserServiceProxy implements UserService{

    private final UserService target;
    private final String role;

    public UserServiceProxy(UserService target, String role) {
        this.target = target;
        this.role = role;
    }

    @Override
    public void deleteUser(String userId) {
        if ("admin".equals(role)) {
            target.deleteUser(userId);
        } else {
            throw new RuntimeException("没有权限删除用户");
        }
    }

}
