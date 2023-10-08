package com.dev.rest.common.validator;


import com.dev.rest.common.annotation.IsMobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileValidator implements ConstraintValidator<IsMobile, String> {
    public MobileValidator() {
    }

    public void initialize(IsMobile isMobile) {
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                String regex = "[1]\\d{10}";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(value);
                return matcher.matches();
            }
        } catch (Exception var4) {
            return false;
        }
    }
}
