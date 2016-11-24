package edu.spring.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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

