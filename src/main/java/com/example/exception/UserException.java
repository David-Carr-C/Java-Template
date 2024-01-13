package com.example.exception;

import lombok.Getter;

@Getter
public class UserException extends Exception{
    private final String reason;

    public UserException(String message, String reason) {
        super(message);
        this.reason = reason;
    }

    public String getDeveloperMessage() {
        return "Error ocurrido en la linea "
                + this.getStackTrace()[0].getLineNumber()
                + " del archivo "
                + this.getStackTrace()[0].getClassName()
                + " en el metodo "
                + this.getStackTrace()[0].getMethodName();
    }
}
