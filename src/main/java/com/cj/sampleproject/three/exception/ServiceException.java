package com.cj.sampleproject.three.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ServiceException extends Exception {

    private final HttpStatus statusCode;
    private final ErrorCode errorCode;
    private final String errorMessage;
    private final String logMessage;
}
