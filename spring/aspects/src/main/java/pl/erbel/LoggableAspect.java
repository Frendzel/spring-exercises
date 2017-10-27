package pl.erbel;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggableAspect {

    @Around("@annotation(pl.erbel.Loggable)")
    public void loggableAdviceAround() {
        System.out.println("Logujemy tu bardzo ważne rzeczy");
    }

    @Before("@annotation(pl.erbel.Loggable)")
    public void loggableAdviceBefore() {
        System.out.println("Tuż przed");
    }

    @After("@annotation(pl.erbel.Loggable)")
    public void loggableAdviceAfter() {
        System.out.println("Za ");
    }

}
