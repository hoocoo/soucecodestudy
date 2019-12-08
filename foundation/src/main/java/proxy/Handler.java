package proxy;

import java.lang.reflect.Method;

public class Handler<T> implements MyInvocationHandler {
    private T obj;
    public Handler(T obj){
        this.obj=obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        System.out.println(123123);
        return method.invoke(obj,args);
    }
}
