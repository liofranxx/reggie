package com.lyc.common;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/13 17:11
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
