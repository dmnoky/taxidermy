package com.dmnoky.taxidermy.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(java.sql.SQLException.class)
    public void handleSQLException(HttpServletRequest request, Exception exception) {
        logger.info("SQLException Occured:: URL="+request.getRequestURL());
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public void handleDataException(HttpServletRequest request, Exception exception) {
        logger.info("DataException Occured:: URL="+request.getRequestURL());
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
    @ExceptionHandler(java.io.IOException.class)
    public void handleIOException() {
        logger.error("IOException handler executed");
    }

}
