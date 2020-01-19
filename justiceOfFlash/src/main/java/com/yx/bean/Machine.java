package com.yx.bean;

/**
 * @author wg
 * @Package com.yx.bean
 * @date 2019/10/22 15:15
 * @Copyright
 */
public class Machine {

    private String name;
    private int time;

    public Machine() {
        System.out.println("machine 构造器执行---");
    }

    public void init() {
        System.out.println("init machine ");
    }

    public void destory() {
        System.out.println("destory machine ");
    }
}
