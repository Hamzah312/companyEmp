package com.Sun.companyEmp.layers.Exceptions;

public class AppException extends RuntimeException{

    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }
}
