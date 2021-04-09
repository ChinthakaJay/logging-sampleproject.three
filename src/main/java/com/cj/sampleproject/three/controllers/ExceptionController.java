package com.cj.sampleproject.three.controllers;

import com.cj.sampleproject.three.exception.ErrorCode;
import com.cj.sampleproject.three.exception.ErrorResponse;
import com.cj.sampleproject.three.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> serviceExceptionHandler(ServiceException ex) {
        log.error(ex.getLogMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode().getValue(), ex.getErrorMessage(), MDC.get(
                "traceId")),
                ex.getStatusCode());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {

        log.error("Unexpected exception occurs: ", ex);
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.SVEX001.getValue(),
                "Unexpected operation failure, please try again", MDC.get("traceId")),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
