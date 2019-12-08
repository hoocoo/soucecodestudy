package cglib;

import javaProxy.HelloServiceImpl;

public class Main {
    public static void main(String[] args) {
        HelloServiceCglib cglib=new HelloServiceCglib();
        HelloServiceImpl helloServiceImpl=(HelloServiceImpl)cglib.getProxy(new HelloServiceImpl());
        helloServiceImpl.sayHello("cglib proxy");
    }
}
