import hoos.spring.bean.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class XmlBeanFactoryTest {
    public static void main(String[] args) {
        //BeanFactory beanFactory=new XmlBeanFactory(new ClassPathResource("beanFactory.xml"));
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanFactory.xml");
        Person person=context.getBean(Person.class);
        System.out.println(person);
    }
}
