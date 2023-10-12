package com.dev.rest.common.autoconfigurer;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "controller.log")
public class LogAopSwitchConfigProperties {
    private boolean enabled = true;

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "LogAopConfigProperties{" +
                "enabled=" + enabled +
                '}';
    }
}
