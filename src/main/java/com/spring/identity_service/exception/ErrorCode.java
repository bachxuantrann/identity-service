package com.spring.identity_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized eror"),
    USER_EXISTED(1002, "User existed"),
    USERNAME_INVALID(1003,"Username must be at least 3 characters")
    ;
    private int code;
    private String message;
}
