package com.yx.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;

/**
 * 切面类
 *
 * @author wg
 * @Package com.yx.aop
 * @date 2019/10/25 11:04
 * @Copyright
 */
@Aspect
public class LogAspect {

    /**
     * 公共 的 切入点表达式  execution(public int com.yx.aop.MathCaculation.*(..))
     */
    @Pointcut("execution(public int com.yx.aop.MathCaculation.*(..))")
    public void pointCut() {
    }

    @Before("execution(public int com.yx.aop.MathCaculation.*(..))) ")
    public void logStart(JoinPoint joinPoint) {
        // 获取参数列表
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("参数 :  " + arg);
        }

        // 获取 方法名
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("signature.getName(); 方法名--  " + name);

        // 获取 全类名 class com.yx.aop.MathCaculation
        Class declaringType = signature.getDeclaringType();
        System.out.println(declaringType);

        // 还是全类名 String 类型
        String declaringTypeName = signature.getDeclaringTypeName();
        System.out.println(declaringTypeName);

        System.out.println("运行开始... 参数列表是 {} ");

    }

    @After("com.yx.aop.LogAspect.pointCut()")
    public void logEnd() {
        System.out.println("运行结束...  ");

    }

    @AfterReturning(value = "pointCut()" ,returning = "result")
    public void logReturn(Object result) {
        System.out.println("运行返回... 结果是 {} "+result);

    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(Exception exception) {
        System.out.println("运行异常... 异常信息是 {} "+exception);

    }
}
