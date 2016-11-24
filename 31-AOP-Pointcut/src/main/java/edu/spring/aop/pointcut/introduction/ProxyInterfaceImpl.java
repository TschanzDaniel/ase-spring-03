package edu.spring.aop.pointcut.introduction;


public class ProxyInterfaceImpl implements ProxyInterface {
    public String getProxyMessage() {
        return "Hello from Proxy!";
    }
}
