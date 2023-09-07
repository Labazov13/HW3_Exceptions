package org.example;

public class NotValidDataException extends Exception{
    String error;
    public NotValidDataException(String message, String error){
        super(message);
        this.error = error;
    }
}
