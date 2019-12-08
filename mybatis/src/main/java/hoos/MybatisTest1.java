package hoos;
import hoos.entity.Account;
import hoos.mapper.AccountMapper;
import hoos.mapper.UserMapper;
import hoos.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MybatisTest1 {
    private static  SqlSession sqlSession;

    public static void main(String[] args) {
       //testGetAccount();
       //testGetUser();
        testGetAccountUserHikariDs();
    }
    public static void testGetAccount(){
        sqlSession = MybatisUtil.getSqlSessionFactory().openSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        //Account account = accountMapper.getAccount("1");
        List<Account> account = accountMapper.getAccounts();
        System.out.println(account.size());
    }
    public static void testGetAccountUserHikariDs(){
        sqlSession = MybatisUtil.getSqlSessionFactoryNoXmlConfigUseHikari().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(userMapper.selectById("1"));
    }
    public static void testGetUser(){
        sqlSession = MybatisUtil.getSqlSessionFactoryNoXmlConfig().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(userMapper.selectById("1"));
    }
}
