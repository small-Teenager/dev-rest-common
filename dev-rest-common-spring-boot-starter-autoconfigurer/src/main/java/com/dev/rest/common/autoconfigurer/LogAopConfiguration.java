package com.dev.rest.common.autoconfigurer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({LogAopConfigProperties.class})
@ConditionalOnProperty(prefix = "dev.rest.controller.log", name = "enabled", matchIfMissing = true)
public class LogAopConfiguration {
        static {
        System.err.println("LogAopConfiguration init...");
    }
    @ConditionalOnMissingBean
    @Bean
    public LogAopService logAopService(){
        LogAopService aop = new LogAopService();

        return new LogAopService();
    }
}
