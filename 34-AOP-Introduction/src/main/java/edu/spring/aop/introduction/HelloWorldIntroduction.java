package edu.spring.aop.introduction;



import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

import org.springframework.stereotype.Component;

import edu.spring.aop.introduction.fancy.FancyMessagePrinter;
import edu.spring.aop.introduction.fancy.FancyMessagePrinterImpl;

@Aspect
@Component
public class HelloWorldIntroduction {

    @DeclareParents(
            value = "edu.spring.aop.introduction.HelloWorldService",
            defaultImpl = FancyMessagePrinterImpl.class
    )
    public FancyMessagePrinter fancyMessagePrinter;

}
