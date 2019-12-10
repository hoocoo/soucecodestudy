package hoos.spring.propertyEditor;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;

/**
 * 自定义属性编辑器
 * 可以将注入的字符串类型转换成date类型
 */
public class MyDatePropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        LocalDateTime dateTime = LocalDateTime.parse(text);
        this.setValue(dateTime);
    }
}
