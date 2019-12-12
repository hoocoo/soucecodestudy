package hoos.spring.listener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ListenerTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ListenerConfig.class);
        TestEvent event=new TestEvent("hello","msg");
        context.publishEvent(event);
    }
}
