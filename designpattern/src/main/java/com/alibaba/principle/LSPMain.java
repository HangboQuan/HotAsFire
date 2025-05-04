package com.alibaba.principle;

/**
 * @author quanhangbo
 * @date 2025-05-04 22:58
 */
public class LSPMain {

    /**
     * 里氏替换原则
     *
     * 背景：
     * 继承的优点：
     *  1. 代码共享，子类拥有父类的方法和属性
     *  2. 代码复用
     *  3. 继承或者重写父类的方法可以实现代码扩展
     *  继承的缺点：
     *  1. 耦合性高，子类和父类强耦合，父类的修改会影响到子类，继承层次过深，会导致代码难以理解和维护
     *  2. 破坏封装，具有侵入性，子类必须继承父类才能使用父类的方法
     *  3. 灵活性差，继承是静态的，子类在编译的时候就去确定了与父类的关系，无法在运行时动态改变继承关系
     *
     *  a. 在类中调用其他类必须要使用父类或接口，如果不能使用父类或接口，则说明违反LSP原则
     *  注：如果子类不能完整实现父类的方法，父类的某些方法在子类中已经发生畸变（） => 建议采用依赖、组合、聚合等关系
     *  b.
     *
     * @param args
     */
    public static void main(String[] args) {
        Soldier soldier = new Soldier(new HandGun());
        soldier.killEnemy();
    }
}
