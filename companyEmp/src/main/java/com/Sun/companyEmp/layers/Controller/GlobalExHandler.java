package com.Sun.companyEmp.layers.Controller;

import com.Sun.companyEmp.layers.Exceptions.AppException;
import com.Sun.companyEmp.layers.Exceptions.DataNotFoundException;
import com.Sun.companyEmp.layers.Exceptions.SemanticException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExHandler {

    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleDNFE(DataNotFoundException dataNotFoundException){
        return new ErrorMessage(dataNotFoundException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(SemanticException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleSemanticE(SemanticException semanticException){
        return new ErrorMessage(semanticException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(AppException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleAppE(AppException appException){
        return new ErrorMessage("something wrong in the request");
    }
}
