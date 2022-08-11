package com.example.validation.config;

import com.example.validation.aspect.UserAspect;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true) //Sử d
public class Java {
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**/
    @Bean
    public UserAspect userAspect(){
        return new UserAspect();
    }
}
