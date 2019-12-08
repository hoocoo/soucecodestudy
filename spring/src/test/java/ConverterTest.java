
import hoos.spring.config.ConverterConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConverterTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ConverterConfig.class);

        System.out.println(123);
    }
}
