package homework8;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowcaseRunner {

    private final ShowcaseOnClass showcaseOnClass;
    private final ShowcaseOnMethod showcaseOnMethod;

    @EventListener(ApplicationReadyEvent.class)
    public void onAppReady() {
        System.out.println("Annotation On Class:");
        showcaseOnClass.showcaseTimer();
        System.out.println("Annotation On Method:");
        showcaseOnMethod.showcaseTimer();
        System.out.println("No Exception: " + showcaseOnMethod.showcaseNoException());
        System.out.println("Exception int: " + showcaseOnMethod.showcaseExceptionInt());
        System.out.println("Exception String: " + showcaseOnMethod.showcaseExceptionString());
        System.out.println("Exception boolean: " + showcaseOnMethod.showcaseExceptionBoolean());
        System.out.println("Exception double: " + showcaseOnMethod.showcaseExceptionDouble());
        System.out.println("noRecoveryFor different to thrown Exception: " +
                showcaseOnMethod.showcaseNoRecoveryForDifferent());
        try {
            System.out.println("noRecoveryFor same as thrown Exception: " + showcaseOnMethod.showcaseNoRecoveryFor());
        } catch (Exception e) {
            System.out.println("Exception in " + e.getMessage() + ": " + e.getClass());
        }
        try {
            System.out.println("noRecoveryFor super of thrown Exception: " +
                    showcaseOnMethod.showcaseNoRecoveryForHierarchy());
        } catch (Exception e) {
            System.out.println("Exception in " + e.getMessage() + ": " + e.getClass());
        }
        try {
            System.out.println("noRecoveryFor array contains thrown Exception: " +
                    showcaseOnMethod.showcaseNoRecoveryForArray());
        }  catch (Exception e) {
            System.out.println("Exception in " + e.getMessage() + ": " + e.getClass());
        }
        System.out.println("noRecoveryFor array doesn't contain thrown Exception: " +
                showcaseOnMethod.showcaseNoRecoveryForArrayNoMatch());
    }
}
