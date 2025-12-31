package com.sevketbuyukdemir.product_service.exception;

import com.sevketbuyukdemir.product_service.constant.ExceptionResponseMessages;
import com.sevketbuyukdemir.product_service.constant.ResponseStatusMessage;
import com.sevketbuyukdemir.product_service.response.BaseResponse;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.concurrent.RejectedExecutionException;

@ControllerAdvice
public class ExceptionResponseHandler {

    @ExceptionHandler(value = { Exception.class, ClassNotFoundException.class })
    public ResponseEntity<BaseResponse> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(prepareExceptionResponse(ExceptionResponseMessages.GENERIC_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<BaseResponse> exceptionBadRequestHandler(IllegalArgumentException illegalArgumentException) {
        return new ResponseEntity<>(prepareExceptionResponse(ExceptionResponseMessages.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { DataIntegrityViolationException.class, PersistenceException.class, SQLException.class })
    public ResponseEntity<BaseResponse> exceptionPersistenceExceptionHandler(SQLException sqlException) {
        return new ResponseEntity<>(prepareExceptionResponse(ExceptionResponseMessages.SQL_EXCEPTION.toString()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = { RejectedExecutionException.class })
    public ResponseEntity<BaseResponse> rejectedExecutionExceptionHandler(RejectedExecutionException rejectedExecutionException) {
        return new ResponseEntity<>(prepareExceptionResponse(ExceptionResponseMessages.REJECTED_EXECUTION_EXCEPTION.toString()), HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }

    private static ExceptionResponse prepareExceptionResponse(String message) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(ResponseStatusMessage.FAILURE.toLower());
        exceptionResponse.setMessage(message);
        return exceptionResponse;
    }
}
