import hoos.spring.config.CircularConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CircularReferencesTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(CircularConfig.class);
        System.out.println(1234);
    }
}
