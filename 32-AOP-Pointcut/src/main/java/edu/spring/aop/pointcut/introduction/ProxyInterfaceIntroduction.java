package edu.spring.aop.pointcut.introduction;



import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProxyInterfaceIntroduction {

    @DeclareParents(
            value = "edu.spring.aop.pointcut.example.HelloWorldService",
            defaultImpl = ProxyInterfaceImpl.class
    )
    public ProxyInterface proxyInterface;
}
