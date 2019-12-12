package hoos.spring.importBeanDefinitionRegistrar;

import hoos.spring.aop.AopConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TestImportBeanDefinitionRegistrar.class)
public class TestConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(TestConfig.class);
        Test bean=(Test)context.getBean("test");
        System.out.println(bean);
    }
}
