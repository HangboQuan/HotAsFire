package com.alibaba.proxy;

public class UserServiceMain {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserService proxy = new UserServiceProxy(userService, "admin");
        proxy.deleteUser("123");

        // 从业务的角度来讲：必须确保只能访问代理对象，下面这种实现方式暴露原对象，导致仍然可以绕过权限来执行
        // 实际项目中别人能看到UserServiceImpl，如果直接new出来，再调用deleteUser还是绕过了权限，那么这个代理模式的代码就显得毫无意义
        // （代理模式本身不强制安全隔离，知识结构性的增强手段）
        userService.deleteUser("123");


        // 优化后 （构造器私有 + 工厂返回代理）
        UserService userService1 = UserServiceFactory.createUserService();
        userService1.deleteUser("123");

    }
}
