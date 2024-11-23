package com.alibaba.strategy;

/**
 * @author quanhangbo
 * @date 2024-11-23 22:20
 */
public enum EnumCalculator {

    ADD("+") {
        public int exec(int a, int b) {
            return a + b;
        }
    },

    SUB("-") {
        public int exec(int a, int b) {
            return a - b;
        }
    };;

    String value = "";

    private EnumCalculator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    /**
     * 策略枚举
     * @param a
     * @param b
     * @return
     */
    public abstract int exec(int a, int b);
}
