package com.yx.aop;

/**
 * 目标类
 * @author wg
 * @Package com.yx.aop
 * @date 2019/10/25 11:08
 * @Copyright
 */
public class MathCaculation {


    public int div(int a,int b){
        System.out.println("进入目标类 MathCaculation  div()..");
        int result= a/b;
        return result;
    }
}
