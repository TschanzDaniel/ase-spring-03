package edu.spring.aop;


import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.spring.aop.example.Car;

public class MainAOPAuto {

    public static void main(String[] args) {

    	AbstractApplicationContext context = new ClassPathXmlApplicationContext("aop-config.xml");

        Car car = context.getBean(Car.class);
        car.addFuel(20);
        car.startEngine();
        car.drive();
        context.close();
        
    }

}
