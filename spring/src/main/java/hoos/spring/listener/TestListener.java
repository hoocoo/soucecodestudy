package hoos.spring.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class TestListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof TestEvent){
            TestEvent textEvent=(TestEvent)event;
            textEvent.print();
        }
    }
}
