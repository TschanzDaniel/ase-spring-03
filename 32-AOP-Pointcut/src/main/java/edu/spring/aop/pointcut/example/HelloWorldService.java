package edu.spring.aop.pointcut.example;



import org.springframework.stereotype.Component;

@Component("helloWorldService")
public class HelloWorldService extends HelloWorldBase {

    public String getMessage() {
        return "Hello World!";
    }
}
