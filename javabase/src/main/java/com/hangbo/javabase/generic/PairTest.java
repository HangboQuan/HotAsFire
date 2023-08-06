package com.hangbo.javabase.generic;


import static com.hangbo.javabase.generic.PairTest2.*;

/**
 * @author quanhangbo
 * @date 2023/8/6 20:32
 */
public class PairTest {
    public static void main(String[] args) {
        Manager ceo = new Manager(3999, 1666, "quanhangbo");
        Manager cfo = new Manager(2999, 1999, "jeff");

        Pair<Manager> buddies = new Pair<>(ceo, cfo);
        printBuddies(buddies);

        Manager[] managers = {ceo, cfo};
        Pair<Employee> result = new Pair<>();
        minmaxBonus(managers, result);
        System.out.println("first: " + result.getFirst().getBonus() + ", second: " + result.getSecond().getBonus());

        maxminBonus(managers, result);
        System.out.println("first: " + result.getFirst().getBonus() + ", second: " + result.getSecond().getBonus());


    }
}
