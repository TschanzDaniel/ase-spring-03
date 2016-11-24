package edu.spring.aop.pointcut;




import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.spring.aop.pointcut.example.HelloWorldService;
import edu.spring.aop.pointcut.introduction.ProxyInterface;

public class MainAopPointcut {

    public static void main(String[] args)
    {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("aop-config.xml");
        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("helloWorldService");

        System.out.println(helloWorldService.getMessage());
        System.out.println(helloWorldService.getBaseMessage());
        System.out.println(((ProxyInterface)helloWorldService).getProxyMessage());
        context.close();
    }

}
