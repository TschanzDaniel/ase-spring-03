package edu.spring.aop.pointcut;



import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointcutAspect {

    @Pointcut("execution(* get*())")
    public void allGetMethods() {}

    @Pointcut("target(edu.spring.aop.pointcut.example.HelloWorldService)")
    public void helloWorldService() {}

    @Pointcut("within(edu.spring.aop.pointcut.example.HelloWorldService)")
    public void helloWorldWithin() {}

    @Pointcut("this(edu.spring.aop.pointcut.introduction.ProxyInterface)")
    public void helloWorldThis() {}

    @Before("allGetMethods() && helloWorldService()")
    public void targetAdvice() {
        System.out.println("Target Advice ausgeführt");
    }

    @Before("allGetMethods() && helloWorldWithin()")
    public void withinAdvice() {
        System.out.println("Within Advice ausgeführt");
    }

    @Before("allGetMethods() && helloWorldThis()")
    public void thisAdvice() {
        System.out.println("This Advice ausgeführt");
    }
}
