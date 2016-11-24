package edu.spring.aop.introduction;



import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.spring.aop.introduction.fancy.FancyMessagePrinter;

public class MainAopIntroduction {

    public static void main(String[] args) {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("aop-config.xml");
        HelloWorldService helloWorldService = (HelloWorldService)ctx.getBean("helloWorldService");

        System.out.println(helloWorldService.getMessage());

        FancyMessagePrinter fancyMessagePrinter = (FancyMessagePrinter)helloWorldService;
        System.out.println(fancyMessagePrinter.printMessage());
        ctx.close();
    }

}
