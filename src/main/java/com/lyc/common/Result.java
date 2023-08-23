package com.lyc.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/10 16:53
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;
    private Map map = new HashMap();

    public static <T> Result<T> success (T object){
        Result<T> result = new Result<>();
        result.data = object;
        result.code = Code.LOGIN_SUCCESS;
        return result;
    }

    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = Code.LOGIN_ERROR;
        return result;
    }

    public Result<T> add(String key, Object value){
        this.map.put(key, value);
        return this;
    }
}
