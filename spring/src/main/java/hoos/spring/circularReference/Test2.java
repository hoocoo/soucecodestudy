package hoos.spring.circularReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test2 {
    @Autowired
    private Test1 test1;
}
