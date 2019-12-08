package hoos.utils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hoos.entity.Account;
import hoos.entity.User;
import hoos.mapper.UserMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory=null;

    /**
     * 使用mybatis主配置文件初始化mybatis
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory(){
        if (sqlSessionFactory==null){
            String resource="mybatisConfig";
            try {
                InputStream resourceAsStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory=new SqlSessionFactoryBuilder().build(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }
    /**
     * 使用java代码方式初始化mybatis
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactoryNoXmlConfig(){
        PooledDataSource pooledDataSource=new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("root");
        TransactionFactory transactionFactory=new JdbcTransactionFactory();
        Environment environment = new Environment("develop", transactionFactory, pooledDataSource);
        Configuration configuration = new Configuration(environment);
        //注册一个别名
        configuration.getTypeAliasRegistry().registerAlias("user", User.class);
        //加入一个映射器
        configuration.addMapper(UserMapper.class);

        sqlSessionFactory=new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

    /**
     * 使用Hikari数据库连接池
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactoryNoXmlConfigUseHikari(){
        HikariConfig config=new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8");
        config.setUsername("root");
        config.setPassword("root");
        HikariDataSource dataSource=new HikariDataSource(config);
        TransactionFactory transactionFactory=new JdbcTransactionFactory();
        Environment environment = new Environment("develop", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        //注册一个别名
        configuration.getTypeAliasRegistry().registerAlias("user", User.class);
        //加入一个映射器
        configuration.addMapper(UserMapper.class);

        sqlSessionFactory=new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }
}
