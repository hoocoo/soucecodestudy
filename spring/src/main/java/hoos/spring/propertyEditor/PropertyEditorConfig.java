package hoos.spring.propertyEditor;

import org.apache.xbean.propertyeditor.CollectionUtil;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyEditor;
import java.time.LocalDateTime;
import java.util.*;

@Configuration
public class PropertyEditorConfig {
    @Bean
    DatePropertyEditorRegistrar datePropertyEditorRegistrar(){
        return new DatePropertyEditorRegistrar();
    }
    @Bean
    MyDatePropertyEditor myDatePropertyEditor(){
        return new MyDatePropertyEditor();
    }
    @Bean
    public CustomEditorConfigurer customEditorConfigurer(){
        //方法- 自定义属性编辑器
        CustomEditorConfigurer customEditorConfigurer=new CustomEditorConfigurer();
        Map<Class<?>, Class<? extends PropertyEditor>> customEditors=new HashMap<>();
        customEditors.put(LocalDateTime.class,MyDatePropertyEditor.class);
        customEditorConfigurer.setCustomEditors(customEditors);
        //方法二 spring 自带的属性编辑器
        List<PropertyEditorRegistrar> propertyEditorRegistrars=new ArrayList<PropertyEditorRegistrar>();
        propertyEditorRegistrars.add(datePropertyEditorRegistrar());
        customEditorConfigurer.setPropertyEditorRegistrars(propertyEditorRegistrars.toArray(new PropertyEditorRegistrar[propertyEditorRegistrars.size()]));
        return customEditorConfigurer;
    }
}
