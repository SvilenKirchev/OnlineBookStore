package com.svilen.onlinebookstore.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "News was not found!")
public class NewsInvalidNameException extends RuntimeException{
    private int statusCode;

    public NewsInvalidNameException() {
        this.setStatusCode(409);
    }

    public NewsInvalidNameException(String message) {
        super(message);
        this.setStatusCode(409);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
