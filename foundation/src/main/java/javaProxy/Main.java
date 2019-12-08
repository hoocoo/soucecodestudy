package javaProxy;

public class Main {
    public static void main(String[] args) {
        HelloServiceProxy helloServiceProxy=new HelloServiceProxy();
        IHelloService helloService=(IHelloService)helloServiceProxy.bind(new HelloServiceImpl());
        helloService.sayHello("java proxy");
    }
}
