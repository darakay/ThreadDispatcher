package com.darakay.oop_projects.ThreadDispatcher;

import java.util.function.Consumer;

public class Result<T> {

    private T value;
    private String message;

    private Result(T value){
        this.value = value;
    }

    private Result(String message){
        this.message = message;
    }

    private Result(String message, T value){
        this.value = value;
        this.message = message;
    }

    public static <T> Result ok(T value){
        return new Result(value);
    }

    public static <T> Result fail(String message){
        return new Result<T>(message);
    }

    public static <T> Result fail(String message, T defaultValue){
        return new Result(message, defaultValue);
    }

    public void callback(Consumer<T> callback){
        if(isSuccess())
            callback.accept(value);
    }

    private boolean isSuccess(){
        return message == null;
    }

    public T getValue(){
        return value;
    }

    public String getMessage() {
        return message;
    }
}
