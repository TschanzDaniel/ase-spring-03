package edu.spring.aop.joinpoint;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("target(edu.spring.aop.joinpoint.example.HelloWorldService)")
    public void logging(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        System.out.println(String.format(
                "[LOG] - [%s] - Method: %s",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()
        ));

        if (args.length > 0) {
            StringBuilder builder = new StringBuilder("Arguments:\n");
            for (Object o : args){
                builder.append(o.toString());
                builder.append('\n');
            }

        }
    }
}
