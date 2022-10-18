package com.example.JwtDemo.Exceptions;

public class UnAuthorizedException extends Exception{
    public UnAuthorizedException(String msg){
        super(msg);
    }
}
