package proxy;

import java.util.Map;
public class PorxyFactory {
    //单interface的时候用
    public static Object newProxyInstance(Class<?> c,MyInvocationHandler h) throws Exception{

        String classStr = ClassUtil.mackProxyClass(c);
        Map<String, byte[]> m = DynamicLoader.compile(classStr);
        DynamicLoader.MemoryClassLoader classLoader = new DynamicLoader.MemoryClassLoader(m);
        Class<?> proxy =classLoader.loadClass(m.keySet().toArray(new String[0])[0]);
        return proxy.getConstructor(MyInvocationHandler.class).newInstance(h);
    }

    //多interface的时候用
    public static Object newProxyInstancewWithMultiClass(Class<?>[] c,MyInvocationHandler h) throws Exception{

        String classStr = ClassUtil.mackMultiProxyClass(c);
        System.out.println (classStr);
        Map<String, byte[]> m = DynamicLoader.compile(classStr);
        DynamicLoader.MemoryClassLoader classLoader = new DynamicLoader.MemoryClassLoader(m);
        Class<?> proxy =classLoader.loadClass(m.keySet().toArray(new String[0])[0]);
        return proxy.getConstructor(MyInvocationHandler.class).newInstance(h);
    }
}