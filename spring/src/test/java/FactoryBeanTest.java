import hoos.spring.bean.Car;
import hoos.spring.bean.CarFactoryBean;
import hoos.spring.bean.Person;
import hoos.spring.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryBeanTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MainConfig.class);
        Car car = (Car)context.getBean("car");
        CarFactoryBean carFactoryBean1 = (CarFactoryBean)context.getBean("&car");
        CarFactoryBean carFactoryBean2 = (CarFactoryBean)context.getBean("&car");
        System.out.println(carFactoryBean1);
        System.out.println(carFactoryBean2);
    }
}
