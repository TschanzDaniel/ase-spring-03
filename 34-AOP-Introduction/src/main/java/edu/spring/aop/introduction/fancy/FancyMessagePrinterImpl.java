package edu.spring.aop.introduction.fancy;


public class FancyMessagePrinterImpl implements FancyMessagePrinter {

    @Override
    public String printMessage() {
        return "Hello from Introduction!";
    }
}
