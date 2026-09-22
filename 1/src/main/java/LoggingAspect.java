import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import java.util.Arrays;

@Aspect
public class LoggingAspect {

    @Before("execution(* Main.*(..)) || execution(* Person.*(..)) || execution(* Student.*(..)) || execution(* Teacher.*(..)) || execution(* Employee.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[LOG] Вызов метода: " + joinPoint.getSignature().getName());
        System.out.println("[LOG] Параметры: " + Arrays.deepToString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* Main.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[LOG] Метод " + joinPoint.getSignature().getName() + " вернул: " + result);
    }

    @AfterThrowing(pointcut = "execution(* Main.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("[LOG] Исключение в методе " + joinPoint.getSignature().getName() + ": " + error);
    }
}