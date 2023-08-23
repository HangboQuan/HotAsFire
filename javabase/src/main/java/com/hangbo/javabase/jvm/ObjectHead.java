package com.hangbo.javabase.jvm;

import lombok.Data;
import lombok.experimental.Accessors;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.nio.ByteOrder;

/**
 * @author quanhangbo
 * @date 2023/8/23 10:09
 */
public class ObjectHead {

    public static void main(String[] args) {
        System.out.println(ByteOrder.nativeOrder());

        System.out.println(VM.current().details());

        Object obj = new Object();
        RunPlan rp = new RunPlan();
        System.out.println(ClassLayout.parseClass(obj.getClass()).toPrintable());

        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println(ClassLayout.parseInstance(rp).toPrintable());
    }

    @Data
    @Accessors
    static public class RunPlan {
        private String actor;
        private String date;
    }
}
