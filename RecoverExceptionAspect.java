package homework8;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RecoverExceptionAspect {

    @Pointcut("@annotation(homework8.RecoverException)")
    public void annotatedMethod(){}

    @Around("annotatedMethod()")
    public Object recoverException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            Class<? extends RuntimeException>[] noRecoverFor = extractNoRecoverFor(joinPoint);
            boolean throwFurther = false;
            for (Class<? extends RuntimeException> exception : noRecoverFor) {
                if (exception.isAssignableFrom(e.getClass())) {
                    throwFurther = true;
                    break;
                }
            }
            if (throwFurther) {
                throw e;
            }
            Class<?> returnType = getReturnType(joinPoint);
            if (!returnType.isPrimitive()) {
                return null;
            }
            if (returnType.isAssignableFrom(boolean.class)) {
                return false;
            }
            if (returnType.isAssignableFrom(double.class) || returnType.isAssignableFrom(float.class)) {
                return 0.0;
            }
            return 0;
        }
    }

    private Class<? extends RuntimeException>[] extractNoRecoverFor(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(RecoverException.class).noRecoveryFor();
    }

    private Class<?> getReturnType(ProceedingJoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getReturnType();
    }
}
