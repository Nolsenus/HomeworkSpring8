package homework8;


import org.springframework.stereotype.Component;

@Component
@Timer
public class ShowcaseOnClass {

    public void showcaseTimer() {
        int j = 0;
        for (int i = 1; i < 10_000_000; i++) {
            if (j % i == 0) {
                j++;
            } else {
                j--;
            }
        }
        System.out.println(j);
    }
}
