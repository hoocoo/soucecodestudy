package hoos.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"hoos.spring.circularReference"})
public class CircularConfig {
}
