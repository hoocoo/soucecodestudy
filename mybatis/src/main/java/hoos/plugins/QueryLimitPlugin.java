package hoos.plugins;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class,Integer.class}
        )
})
public class QueryLimitPlugin implements Interceptor {
    private int limit;
    private String dbType;
    //中间表别名，避免表重名
    private static final String LMT_TABLE_NAME="limit_Table_name_xxx";
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler stmtHandler=(StatementHandler) invocation.getTarget();
        MetaObject metaStmtHandler= SystemMetaObject.forObject(stmtHandler);
        //分离代理对象，从而形成多次代理，通过两次循环最原始的被代理类，Mybatis
        //使用的是JDK代理
        while (metaStmtHandler.hasGetter("h")){
            Object object=metaStmtHandler.getValue("h");
            metaStmtHandler=SystemMetaObject.forObject(object);
        }
        //分离最后一个代理对象的目标类
        while (metaStmtHandler.hasGetter("target")){
            Object object=metaStmtHandler.getValue("target");
            metaStmtHandler=SystemMetaObject.forObject(object);
        }
        //取出即将要执行的sql
        String sql=(String)metaStmtHandler.getValue("delegate.boundSql.sql");
        StringBuilder limitSql=new StringBuilder();
        //判断参数是不是Mysql数据库且SQL有没有被插件重写过
        if("mysql".equals(dbType)&&sql.indexOf(LMT_TABLE_NAME)==-1){
            sql.trim();
            sql=sql.replace(";","");
            limitSql.append("select * from (").append(sql).append(") ").append(LMT_TABLE_NAME).append(" limit ").append(limit);
            metaStmtHandler.setValue("delegate.boundSql.sql",limitSql.toString());
        }
        //调用原来对象的方法，进入责任链的下一层级
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        //使用默认的Mybatis提供的类生成代理对象
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        limit=Integer.parseInt((String)properties.get("limit"));
        dbType=(String)properties.get("dbType");
    }
}
