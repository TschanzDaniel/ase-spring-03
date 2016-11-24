package edu.spring.aop.annotation;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Random;

@Aspect
@Component
public class CarAspect {
    @Pointcut("execution(* edu.spring.aop.example.Car.startEngine())")
    public void startEnginePointcut(){}

    @Pointcut(value = "execution(* edu.spring.aop.example.Car.addFuel(..)) && args(litres)")
    public void addFuelPointcut(int litres){}

    @Around("execution(* edu.spring.aop.example.Car.startEngine())")
    public void aroundStartEngine(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Motor Startversuch");

        Random rn = new Random();
        if (rn.nextInt(100) < 50)
        {
            joinPoint.proceed();
            System.out.println("Motor gestartet");
        }
        else
        System.out.println("Motorstart fehlgeschlagen");
    }

    @Before("startEnginePointcut()")
    public void beforeStartEngine() throws Throwable {
        System.out.println("Vor dem Motorstart");
    }

    @After("startEnginePointcut()")
    public void afterStartEngine() throws Throwable {
        System.out.println("Nach dem Motorstart");
    }

    @Before(value = "addFuelPointcut(litres)")
    public void beforeAddFuel(int litres) {
        System.out.println("Auto Tankauftrag: " + litres);
    }
}
