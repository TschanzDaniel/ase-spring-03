package edu.spring.aop.joinpoint;



import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.spring.aop.joinpoint.example.BankAccount;
import edu.spring.aop.joinpoint.example.HelloWorldService;

public class MainAopJoinPoints {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("aop-config.xml");
        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("helloWorldService");

        BankAccount meinKonto = (BankAccount) context.getBean("bankAccount");
        meinKonto.setAccountID(4);
        meinKonto.insertMoney(200);

        helloWorldService.getMessage();
        context.close();
    }
}
