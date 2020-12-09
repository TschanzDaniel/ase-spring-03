package edu.spring.aop.joinpoint;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import edu.spring.aop.joinpoint.example.BankAccount;

@Aspect
@Component
public class StealerAspect {

    @Autowired
    ApplicationContext context;

    @Autowired
    BankAccount nummernKonto;

    @Around("execution(* edu.spring..*.BankAccount.insertMoney(..))")
    public Object stealMoney(ProceedingJoinPoint joinPoint) throws Throwable {
        if (joinPoint.getTarget().equals(nummernKonto)) {
        	return joinPoint.proceed();
        }
        double amount = (Double)joinPoint.getArgs()[0];

        System.out.println("Klaue " + amount + "€");
        nummernKonto.insertMoney(amount);

        //joinPoint.proceed();
        return true;
    }
}
