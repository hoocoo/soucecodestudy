package hoos.spring.circularReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test1 {
    @Autowired
    private Test2 test2;
}
