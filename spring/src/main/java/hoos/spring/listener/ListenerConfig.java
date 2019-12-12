package hoos.spring.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {
    @Bean
    TestListener testListener() {
        return new TestListener();
    }
}
