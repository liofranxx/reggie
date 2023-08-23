package com.lyc.common;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/13 15:18
 * 基于ThreadLocal的工具类，用于获取当前登录用户的ID
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long ID){
        threadLocal.set(ID);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
