package javaProxy;

public class HelloServiceImpl implements IHelloService{
    @Override
    public void sayHello(String msg) {
        System.out.println("hello"+msg);
    }
}
