package simple.framework.core.sdk;

import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import simple.framework.core.annotation.SDKApi;

import java.util.Set;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/20 15:04
 * Created by huangxy
 */
public class SDKRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor,EnvironmentAware {

    private String [] basePackages;

    public SDKRegisterPostProcessor(String[] basePackages) {
        this.basePackages = basePackages;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {


        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.addIncludeFilter(new AnnotationTypeFilter(SDKApi.class));

        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = scanner.findCandidateComponents(basePackage);
            candidates.forEach((BeanDefinition beanDefinition)->{

                String beanClassName = beanDefinition.getBeanClassName();
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SDKMapperProxyFactoryBean.class);
                builder.setLazyInit(true);
                builder.addPropertyValue("mapperInterface",beanClassName);


                beanDefinition = builder.getBeanDefinition();
                beanDefinition.setPrimary(true);

                BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, beanClassName,new String[]{beanClassName+"SDK"});
                BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);

            });
        }
    }

    private ClassPathScanningCandidateComponentProvider getScanner(){
        return new ClassPathScanningCandidateComponentProvider(false,this.environment){
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                AnnotationMetadata metadata = beanDefinition.getMetadata();
                return metadata.hasAnnotation(SDKApi.class.getName());
            }
        };
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
