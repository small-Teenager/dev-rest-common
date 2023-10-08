package com.dev.rest.common.annotation;

import com.dev.rest.common.enums.SensitiveEnum;
import com.dev.rest.common.validator.SensitiveSerialize;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerialize.class)
public @interface SensitiveWrapped {

    /**
     * 脱敏类型
     * @return
     */
    SensitiveEnum value();
}

