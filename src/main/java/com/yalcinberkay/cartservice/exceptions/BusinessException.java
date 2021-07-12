package com.yalcinberkay.cartservice.exceptions;

import java.util.function.Supplier;

public class BusinessException extends BaseException implements Supplier<BaseException> {

    private static final long serialVersionUID = 4378209836924571177L;

    public BusinessException(String key) {
        super(key);
    }

    public BusinessException(String key, String... args) {
        super(key, args);
    }

    @Override
    public BusinessException get() {
        return new BusinessException(getKey(), getArgs());
    }
}