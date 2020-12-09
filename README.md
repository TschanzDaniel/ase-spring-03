# ase-spring-03
Aspect Oriented Programming with Spring

## Version

Spring frame work version: 5.2.7.RELEASE

## Project 31 AOP Auto

Show aspects around _starting_ and _fueling_ a car.
The _preconditions_ are the _dependencies_ for aspects in the _pom file_.
The configurations in this example are done with a _beans.xml_ file.
It has aspects defined through the beeans.xml file and aspects through annotations.

```xml
    <context:component-scan base-package="edu.spring"/>
    <aop:aspectj-autoproxy/>
    
    <!-- XML Konfiguration -->
    <bean id="xmlAspect" class="edu.spring.aop.xml.SampleXMLAspect"/>

    <aop:config>

        <aop:aspect ref="xmlAspect">
            <aop:before method="logMessage" pointcut="execution(* edu.spring..*.Car.drive(..))"/>
        </aop:aspect>

    </aop:config>

```

Example of the _Monitoring aspect_ with an _around advice_.

```java
@Component
@Aspect
public class MonitoringAspect {
	@Pointcut("execution(* edu.spring.aop.example..*(..))")
	//@Pointcut("execution(private * *(..))")	
	private void all() {}

	@Around("all()")
	public Object printParametersAndReturnVal(ProceedingJoinPoint joinPoint) throws Throwable {
		
			System.out.println(". Klasse    : " + joinPoint.getTarget().getClass());
			System.out.println(". Methode   : " + joinPoint.getSignature().getName());
			System.out.print  (". Argumente : ");
			for(Object arg : joinPoint.getArgs()) System.out.print(arg + " / ");

			Object ret = joinPoint.proceed();

			System.out.println("\n. Return    : " + ret + "\n");

			return ret;
	}
}
```

The methods of the following car class do not contain any code. All code is provided through _aspects_.

```java
@Component
public class Car {
    public void startEngine() {
    }

    public void addFuel(int litres) {
    }

    public void drive() {
    }
    
}
```

The empty methods of the _Car class_ are called from the _main method_.

```java
    public static void main(String[] args) {

    	AbstractApplicationContext context = new ClassPathXmlApplicationContext("aop-config.xml");

        Car car = context.getBean(Car.class);
        car.addFuel(20);
        car.startEngine();
        car.drive();
        context.close();
        
    }

```

## Project 32 AOP Pointcut

This example shows the pointcut options _target_, _withIn_ and _this_.

- target pointcut: all methods having the interface edu.spring.aop.pointcut.example.HelloWorldService
- within pointcut: all methods within edu.spring.aop.pointcut.example.HelloWorldService
- this pointcut: all methods implementing ProxyInterface (via ProxyInterfaceIntroduction)

```java
@Aspect
@Component
public class PointcutAspect {

    @Pointcut("execution(* get*())")
    public void allGetMethods() {}

    @Pointcut("target(edu.spring.aop.pointcut.example.HelloWorldService)")
    public void helloWorldService() {}

    @Pointcut("within(edu.spring.aop.pointcut.example.HelloWorldService)")
    public void helloWorldWithin() {}

    @Pointcut("this(edu.spring.aop.pointcut.introduction.ProxyInterface)")
    public void helloWorldThis() {}

    // all methods having the interface edu.spring.aop.pointcut.example.HelloWorldService
    @Before("allGetMethods() && helloWorldService()")
    public void targetAdvice() {
        System.out.println("Target Advice ausgeführt");
    }

    // all methods within edu.spring.aop.pointcut.example.HelloWorldService
    @Before("allGetMethods() && helloWorldWithin()")
    public void withinAdvice() {
        System.out.println("Within Advice ausgeführt");
    }

    // all methods implementing ProxyInterface (via ProxyInterfaceIntroduction)
    @Before("allGetMethods() && helloWorldThis()")
    public void thisAdvice() {
        System.out.println("This Advice ausgeführt");
    }
}

```

How ever... the mostly used pointcut option is execution. the was used in example 31.

## Project 33 AOP JoinPoints

The join point example shows how to use aspects for manipulating transctions.
A stealer aspect is used to book the transation to a number account.
It is realized with an around aspect. The proceeding joint point gives
access to the method.

```java
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

```

## Project 34 AOP Introduction

The class HelloWorldService shall get extend by the FancyMessagePrinter

```java
@Aspect
@Component
public class HelloWorldIntroduction {

    @DeclareParents(
            value = "edu.spring.aop.introduction.HelloWorldService",
            defaultImpl = FancyMessagePrinterImpl.class
    )
    public FancyMessagePrinter fancyMessagePrinter;

}
```

The FancyMessagePrinter method .printMessage() can be accessed through the helloWorldService.

```java
        HelloWorldService helloWorldService = (HelloWorldService)ctx.getBean("helloWorldService");

        System.out.println(helloWorldService.getMessage());

        FancyMessagePrinter fancyMessagePrinter = (FancyMessagePrinter)helloWorldService;
        System.out.println(fancyMessagePrinter.printMessage());
```



