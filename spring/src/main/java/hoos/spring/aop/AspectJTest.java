package hoos.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectJTest {
    /**
     * 配置横切点
     */
    @Pointcut("execution(* hoos.spring.aop.TestBean.test(..))")
    public void test(){}

    /**
     * 环绕通知执行后--连接点执行前执行
     */
    @Before("test()")
    public void beforeTest(){
        System.out.println("before test");
    }

    /**
     * 环绕通知执行完毕后执行
     */
    @After("test()")
    public void afterTest(){
        System.out.println("after test");
    }

    /**
     * 环绕通知
     * ProceedingJoinPoint 连接点
     * @param p
     * @return
     */
    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p){
        System.out.println("before1 test");
        Object o=null;
        try {
            o = p.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after1 test");
        return o;
    }
}
