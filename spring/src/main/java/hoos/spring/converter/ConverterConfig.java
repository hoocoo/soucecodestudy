package hoos.spring.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConverterConfig {
    @Bean
    String2DateConverter converter(){
        String2DateConverter converter=new String2DateConverter();
        converter.setDatePattern("yyyy-MM-dd");
        return converter;
    }
    @Bean
    public ConversionServiceFactoryBean conversionService(){
        ConversionServiceFactoryBean conversionServiceFactoryBean=new ConversionServiceFactoryBean();
        Set<Converter> converters=new HashSet<>();
        converters.add(converter());
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean;
    }
}
