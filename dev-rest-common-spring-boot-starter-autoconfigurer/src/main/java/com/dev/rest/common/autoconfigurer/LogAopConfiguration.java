package com.dev.rest.common.autoconfigurer;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({LogAopSwitchConfigProperties.class})
public class LogAopConfiguration {


}
