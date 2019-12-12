package hoos.spring.converter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.Date;

public class ConverterTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ConverterConfig.class);
        GenericConversionService conversionService=(GenericConversionService)context.getBean("conversionService");
        Date date = conversionService.convert("2019-12-11 23:11:23", Date.class);
        System.out.println(date);
    }
}
