package com.dev.rest.common.exception;

public abstract class AbstractException extends RuntimeException {

    protected final String code;

    public AbstractException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
