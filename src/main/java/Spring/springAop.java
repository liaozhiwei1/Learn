package Spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @version: 1.0
 * @description:  spring4 通知顺序 环绕通知->前置通知->执行代码->环绕通知->后置通知 -> 返回通知
 *                                 环绕通知->前置通知->执行代码-> 后置通知 -> 异常通知
 *                spring5 通知顺序 环绕通知->前置通知->执行代码->后置通知 -> 返回通知->环绕通知
 *                                 环绕通知->前置通知->执行代码-> 异常通知 -> 后置通知
 * @author: zhiwei.liao
 * @create: 2020-12-02 10:11:29
 **/
@Component
@Aspect
public class springAop {

    @Before("execution(public * Spring.Service.ServiceA.*(..))")
    public void beforeNotify(){
        System.out.println("前置通知");
    }

    @Around("execution(public int Spring.service.*(..))")
    public Object aroundNotify(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("环绕通知");
        return proceed;
    }
}
