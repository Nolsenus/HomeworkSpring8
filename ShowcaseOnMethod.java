package homework8;

import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class ShowcaseOnMethod {

    @Timer
    public void showcaseTimer() {
        int j = 2;
        for (int i = 1; i < 10_000_000; i++) {
            if (i % (j / 2) == 0) {
                j++;
            } else {
                j--;
            }
        }
        System.out.println(j);
    }

    @RecoverException
    public int showcaseNoException() {
        return 123;
    }

    @RecoverException
    public int showcaseExceptionInt() {
        throw new NoSuchElementException("showcaseException()");
    }

    @RecoverException
    public String showcaseExceptionString() {
        throw new NoSuchElementException("showcaseExceptionString()");
    }

    @RecoverException
    public boolean showcaseExceptionBoolean() {
        throw new NoSuchElementException("showcaseExceptionBoolean()");
    }

    @RecoverException
    public double showcaseExceptionDouble() {
        throw new NoSuchElementException("showcaseExceptionDouble()");
    }

    @RecoverException(noRecoveryFor = NumberFormatException.class)
    public int showcaseNoRecoveryForDifferent() {
        throw new ArrayIndexOutOfBoundsException("showcaseNoRecoveryForDifferent()");
    }

    @RecoverException(noRecoveryFor = NoSuchElementException.class)
    public int showcaseNoRecoveryFor() {
        throw new NoSuchElementException("showcaseNoRecoveryFor()");
    }

    @RecoverException(noRecoveryFor = IllegalArgumentException.class)
    public int showcaseNoRecoveryForHierarchy() {
        throw new NumberFormatException("showcaseNoRecoveryForHierarchy()");
    }

    @RecoverException(noRecoveryFor = {IllegalArgumentException.class, ArrayIndexOutOfBoundsException.class})
    public int showcaseNoRecoveryForArray() {
        throw new ArrayIndexOutOfBoundsException("showcaseNoRecoverForArray()");
    }

    @RecoverException(noRecoveryFor = {IllegalArgumentException.class, ArrayIndexOutOfBoundsException.class})
    public int showcaseNoRecoveryForArrayNoMatch() {
        throw new NoSuchElementException();
    }
}
