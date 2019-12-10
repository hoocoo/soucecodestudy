package hoos.spring.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringValueResolver;

import java.util.HashSet;
import java.util.Set;

public class ObscentityRemovingBFPP implements BeanFactoryPostProcessor {
    private Set<String> obscenties;
    public ObscentityRemovingBFPP() {
        this.obscenties = new HashSet<>();
    }
    public void setObscenties(Set<String> obscenties){
        this.obscenties.clear();
        for (String obscenty : obscenties) {
            this.obscenties.add(obscenty);
        }
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            StringValueResolver stringValueResolver = new StringValueResolver() {
                @Override
                public String resolveStringValue(String strVal) {
                    if(isObcene(strVal))return "****";
                    return strVal;
                }
            };
            BeanDefinitionVisitor beanDefinitionVisitor = new BeanDefinitionVisitor(stringValueResolver);
            beanDefinitionVisitor.visitBeanDefinition(beanDefinition);
        }
    }

    private boolean isObcene(String value) {
        return obscenties.contains(value);
    }
}
