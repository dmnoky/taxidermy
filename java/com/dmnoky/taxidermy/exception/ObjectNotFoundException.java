package com.dmnoky.taxidermy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Object Not Found")
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(Long id, String className) {
        super("Not found "+className+" with id = "+id);
    }

    public ObjectNotFoundException(String article, String className) {
        super("Not found "+className+" with article = "+article);
    }

}
