package com.example.cruddemo.CustomException;

import org.springframework.stereotype.Component;

@Component
public class ControllerException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public ControllerException(){

    }

    public ControllerException(String errorCode,String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(String errorCode){
        this.errorCode = errorCode;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
