package hoos.plugins;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;
@Intercepts({@Signature(
        type= Executor.class,
        method = "update",
args = {MappedStatement.class,Object.class}
)})
public class Myplugin implements Interceptor {
    Properties properties=null;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("开始");
        Object retObj=invocation.proceed();
        System.out.println("结束");
        return retObj;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("调用生成代理对象");
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("获取插件配置的属性"+properties.get("name"));
        this.properties=properties;
    }
}
