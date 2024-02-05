package homework8;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("@within(homework8.Timer)")
    public void withinTimer(){}

    @Pointcut("@annotation(homework8.Timer)")
    public void annotationTimer(){}

    @Around("withinTimer() || annotationTimer()")
    public Object time(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long end = System.nanoTime();
        double timeInSeconds = (end - start) / 1_000_000_000.0;
        log.info("{} - {} #{}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), timeInSeconds);
        return result;
    }
}
