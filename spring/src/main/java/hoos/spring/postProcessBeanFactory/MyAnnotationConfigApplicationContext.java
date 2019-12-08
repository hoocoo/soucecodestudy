package hoos.spring.postProcessBeanFactory;

import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyAnnotationConfigApplicationContext extends AnnotationConfigApplicationContext {
    public MyAnnotationConfigApplicationContext(Class<?>... componentClasses) {
        super(componentClasses);
    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        //特别注意，执行该方法时getBeanDefinition  只能获取到配置相关的等bean定义，业务相关bean还未被定义
        BeanDefinition definition=beanFactory.getBeanDefinition("mainConfig");
//        PropertyValue value=new PropertyValue("name","小胡");
//        definition.getPropertyValues().addPropertyValue(value);
    }
}
