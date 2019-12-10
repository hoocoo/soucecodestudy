package hoos.spring.bfpp;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactoryPostProcessorTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bfppBeanFactory.xml");
        System.out.println(context.getBean("simple"));
    }
}
