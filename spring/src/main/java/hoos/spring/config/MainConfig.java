package hoos.spring.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hoos.spring.bean.CarFactoryBean;
import hoos.spring.bean.Person;
import hoos.spring.converter.String2DateConverter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Set;

@Configuration
@MapperScan({"hoos.spring.mapper"})
@EnableAspectJAutoProxy
public class MainConfig {
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
    @Bean
    public CarFactoryBean car(){
        CarFactoryBean factoryCarBean=new CarFactoryBean();
        factoryCarBean.setCarInfo("超级跑车,200,200000");
        return factoryCarBean;
    }
    @Bean
    public Person person(){
        return new Person();
    }
    @Bean
    public DataSource dataSource() {
        HikariConfig config=new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8");
        config.setUsername("root");
        config.setPassword("root");
        return new HikariDataSource(config);
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }
}
