package hoos.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    @Bean
    TestBean testBean() {
        return new TestBean();
    }
    @Bean
    AspectJTest aspectJTest() {
        return new AspectJTest();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AopConfig.class);
        TestBean bean=(TestBean)context.getBean("testBean");
        bean.test();
    }
}
