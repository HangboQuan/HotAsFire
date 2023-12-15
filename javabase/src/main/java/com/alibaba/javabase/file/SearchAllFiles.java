package com.alibaba.javabase.file;

import java.io.File;

/**
 * @author quanhangbo
 * @date 2023/8/14 14:07
 */
public class SearchAllFiles {
    public static void main(String[] args) {
        String path = "E://books";
        File file = new File(path);
        int level = 0;
        printAllFiles(file, level);
    }

    public static void printAllFiles(File f, int level) {

        for (int i = 0; i < level; i ++ ) {
            System.out.print("\t");
        }
        System.out.println(f.getName());
        if (f.exists() && f.isDirectory()) {
            for (File file : f.listFiles()) {
                printAllFiles(file, level + 1);
            }
        }
    }

    /**
     * 按照目录层级打印 每隔一级目录用\t分隔开
     * books
     * 	algorithm
     * 		ACM国际大学生程序设计竞赛：算法与实现.pdf
     * 		labuladong的算法小抄官方完整版.pdf
     * 		剑指offer 名企面试官精讲典型编程题.pdf
     * 		啊哈！算法.pdf
     * 		大话数据结构.pdf
     * 		挑战程序设计竞赛(第2版).pdf
     * 		数据结构(C语言版).严蔚敏_吴伟民.pdf
     * 		数据结构(清华大学C++语言版).pdf
     * 		数据结构与算法(Java语言版).pdf
     * 		漫画算法.pdf
     * 		算法(第四版).pdf
     * 		算法与数据结构题目最优解 ,左程云著.pdf
     * 		算法导论(第三版).pdf
     * 		算法竞赛入门经典.pdf
     * 		算法竞赛入门经典完整版(ACM竞赛入门经典).pdf
     * 		编程之美.pdf
     * 		编程珠玑(第2版).pdf
     * 	code-language
     * 		c++
     * 			C++ Primer Plus(第6版).pdf
     * 			C++标准程序库.pdf
     * 			C和指针(第2版).pdf
     * 			C程序设计语言.pdf
     * 			Effective C++(第3版).pdf
     * 			More Effective C++中文版.pdf
     * 			STL源码剖析.pdf
     * 			深度探索C++对象模型.pdf
     * 		front-end
     * 			ECMAScript 6入门.pdf
     * 			Effective JavaScript.pdf
     * 			JavaScript DOM编程艺术(第2版).pdf
     * 			JavaScript权威指南(第6版)(中文版).pdf
     * 			JavaScript高级程序设计(第3版).pdf
     * 			Vue.js设计与实现.pdf
     * 			你不知道的JavaScript(上卷).pdf
     * 			你不知道的JavaScript(下卷).pdf
     * 			你不知道的JavaScript(中卷).pdf
     * 			深入浅出Nodejs.pdf
     * 			深入理解ES6.pdf
     * 			锋利的jQuery.pdf
     * 		go
     * 			Go Web 编程.pdf
     * 			GO专家编程.pdf
     * 			Go并发编程实战.pdf
     * 			GO程序设计语言.pdf
     * 			Go语言实战.pdf
     * 		java
     * 			advance
     * 				Effective Java 中文版.pdf
     * 				Head First Java中文版.pdf
     * 				Java并发编程 核心方法与框架.pdf
     * 				Java并发编程实战.pdf
     * 				Java并发编程的艺术.pdf
     * 				Java性能优化权威指南.pdf
     * 				Java性能权威指南.pdf
     * 				Java高并发编程详解-多线程与架构设计.pdf
     * 				Spring技术内幕：深入解析Spring架构与设计原理.pdf
     * 				Spring源码深度解析(第2版).pdf
     * 				图解Java多线程设计模式.pdf
     * 				实战Java高并发程序设计.pdf
     * 				精通Spring.pdf
     * 			base
     * 				Java 8函数式编程.pdf
     * 				Java 8实战.pdf
     * 				Java多线程编程核心技术.pdf
     * 				Java工程师修炼之道.pdf
     * 				Java核心技术卷1：基础知识(第10版).pdf
     * 				Java核心技术卷2：高级特性(第10版).pdf
     * 				Java程序员修炼之道.pdf
     * 				Java编程思想第四版.pdf
     * 				Java编程的逻辑.pdf
     * 				深入浅出Java多线程.pdf
     * 				码出高效Java开发手册.pdf
     * 				阿里巴巴Java开发手册(泰山版).pdf
     * 			interview
     * 				阿里大佬总结的Java面试资料.pdf
     * 			jvm
     * 				实战Java虚拟机  JVM故障诊断与性能优化.pdf
     * 				揭秘Java虚拟机-JVM设计原理与实现.pdf
     * 				深入理解Java虚拟机 JVM高级特性与最佳实践(第3版).pdf
     * 				深入理解Java虚拟机 JVM高级特性与最佳实践.pdf
     * 			microservice architecture
     * 				Apache Kafka实战.pdf
     * 				RabbitMQ实战 高效部署分布式消息队列.pdf
     * 				RocketMQ实战与原理解析.pdf
     * 				Spring Cloud微服务实战.pdf
     * 				ZooKeeper-分布式过程协同技术详解.pdf
     * 				一线架构师实践指南(温昱).pdf
     * 				亿级流量网站架构核心技术.pdf
     * 				从Paxos到Zookeeper  分布式一致性原理与实践.pdf
     * 				分布式服务架构：原理、设计与实战.pdf
     * 				大型分布式网站架构设计与实践.pdf
     * 				大型网站技术架构演进与性能优化.pdf
     * 				架构探险：轻量级微服务架构.pdf
     * 				深入理解Nginx：模块开发与架构解析(第2版).pdf
     * 				重构_改善既有代码的设计.pdf
     * 		python
     * 			Python Crash Course 2nd.pdf
     * 			Python深度学习.pdf
     * 			python编程从入门到实践(第2版).pdf
     * 			流畅的python.pdf
     * 		scala
     * 			Scala-in-Depth.pdf
     * 			Scala函数式编程.pdf
     * 			Scala学习手册.pdf
     * 			Scala编程-第4版.pdf
     * 			Scala编程思想.pdf
     * 			Scala谜题.pdf
     * 			快学Scala.pdf
     * 	computer-common
     * 		代码大全(第2版).pdf
     * 		代码整洁之道.pdf
     * 		码农翻身：用故事给技术加点料.pdf
     * 		程序是怎样跑起来的.pdf
     * 		编码的奥秘.pdf
     * 		编译原理.pdf
     * 		网络是怎样连接的.pdf
     * 		计算之魂.pdf
     * 		计算机是怎样跑起来的.pdf
     * 		计算机程序的构造和解释(第2版).pdf
     * 	computer-english
     * 		再要你命3000.pdf
     * 		薄冰英语语法.pdf
     * 		要你命3000.pdf
     * 	design-pattern
     * 		设计模式之禅.pdf
     * 	Go程序设计语言.pdf
     * 	Java NIO 中文.pdf
     * 	Java NIO 指南 - v1.0.pdf
     * 	Java 反射机制 - v1.0.pdf
     * 	Java 并发性和多线程 - v1.2.pdf
     * 	Java 并发编程 - v1.0.pdf
     * 	Java开发手册 v1.5.0.pdf
     * 	Java攻略：Java常见问题的简单解法.pdf
     * 	JAVA核心技术卷1：基础知识（第8版）.pdf
     * 	Java核心技术卷2：高级特性（第8版）.pdf
     * 	JAVA程序员面试宝典第4版.pdf
     * 	Java编程规范(第3版).pdf
     * 	Java虚拟机规范(Java SE 8版).pdf
     * 	Java语言规范 基于 Java SE 8.pdf
     * 	linux
     * 		性能之巅 洞悉系统、企业与云计算.pdf
     * 	logic
     * 		简单的逻辑学.pdf
     * 		身边的逻辑学.pdf
     * 		风靡世界500强的思维训练题.闵于思.pdf
     * 	math
     * 		具体数学.pdf
     * 		基础拓扑学.pdf
     * 		数学之美.pdf
     * 		普林斯顿微积分读本.pdf
     * 		概率论及其应用(第三版).中文版.pdf
     * 		深度学习的数学.pdf
     * 		离散数学及其应用(原书第6版).pdf
     * 		离散数学及其应用(第6版)-中文版.pdf
     * 		程序员的数学.pdf
     * 		程序员的数学2-概率统计.pdf
     * 		程序员的数学3-线性代数.pdf
     * 		线性代数应该这样学(第3版).pdf
     * 		统计学习方法(第2版)李航.pdf
     * 	network
     * 		HTTP权威指南.pdf
     * 		TCP-IP详解卷1：协议 原书第2版.pdf
     * 		TCP-IP详解卷三：TCP事务协议、HTTP、NNTP和UNIX域协议.pdf
     * 		TCP-IP详解卷二：实现.pdf
     * 		图解HTTP.pdf
     * 		图解TCP-IP.pdf
     * 		计算机网络-第7版-谢希仁.pdf
     * 		计算机网络-自顶向下方法.pdf
     * 	operate-system
     * 		Linux内核设计与实现(第三版).pdf
     * 		UNIX环境高级编程(第3版).pdf
     * 		深入Linux内核架构.pdf
     * 		深入理解Linux内核.pdf
     * 		深入理解计算机系统第三版.pdf
     * 		现代操作系统.pdf
     * 		程序员自我修养.pdf
     * 	redis
     * 		Redis实战.pdf
     * 		Redis开发与运维(付磊).pdf
     * 		Redis深度历险：核心原理和应用实践.pdf
     * 		Redis设计与实现.pdf
     * 	sql
     * 		MongoDB权威指南(第2版).pdf
     * 		MySQL必知必会.pdf
     * 		SQL必知必会(第3版).pdf
     * 		高可用MySQL 构建健壮的数据中心.pdf
     * 		高性能mysql第三版.pdf
     * 	Thinking.In.Java(中文版).pdf
     * 	UNIX网络编程卷1：套接字联网API（第3版）.pdf
     * 	图解HTTP  黑白印刷 [（日）上野宣著][人民邮电出版社][2014.05][250页].pdf
     * 	深度探索C++对象模型.pdf
     * 	王争·数据结构与算法之美.pdf
     * 	算法笔记(胡凡曾磊).pdf
     * 	西电2018校赛现场赛题解整理.pdf
     * 	阿里技术参考图册（研发篇）.pdf
     * 	高性能MySQL(第3版).pdf
     */
}
