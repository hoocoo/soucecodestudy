import hoos.spring.bean.Person;
import hoos.spring.config.MainConfig;
import hoos.spring.mapper.UserMapper;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MainConfig.class);
        //MyAnnotationConfigApplicationContext context=new MyAnnotationConfigApplicationContext(MainConfig.class);

        Person person=(Person)context.getBean("person");

        System.out.println(person);


        BeanDefinition beanDefinition = context.getBeanFactory().getBeanDefinition("userMapper");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            BeanDefinition beanDefinition1 = context.getBeanDefinition(beanDefinitionNames[i]);

            if(beanDefinition1.getClass().getName().equals(ScannedGenericBeanDefinition.class.getName())){
                ScannedGenericBeanDefinition definition=(ScannedGenericBeanDefinition)beanDefinition1;
                System.out.println(123);
            }
            System.out.println(beanDefinition1.getClass().getName()+"#"+beanDefinition1.getBeanClassName());
        }
        List<BeanPostProcessor> beanPostProcessors = context.getDefaultListableBeanFactory().getBeanPostProcessors();
        for (int i = 0; i < beanPostProcessors.size(); i++) {
            System.out.println(beanPostProcessors.get(i));
        }
        //方式一
//        SqlSessionFactory sqlSessionFactory=context.getBean(SqlSessionFactory.class);
//        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
//        UserMapper userMapper=sqlSessionTemplate.getMapper(UserMapper.class);
//        System.out.println(userMapper.selectById("1"));

        //方式二
        UserMapper userMapper=(UserMapper)context.getBean("userMapper");
        System.out.println(userMapper.selectById("1"));
    }
}
