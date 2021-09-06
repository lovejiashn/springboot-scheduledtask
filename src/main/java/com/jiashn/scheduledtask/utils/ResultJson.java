package com.jiashn.scheduledtask.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: jiangjs
 * @description:
 * @date: 2021/9/6 16:22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultJson<T> {

    private int code;
    private String msg;
    private T date;

    public static <T> ResultJson<?> success(){
        return ResultJson.builder()
                .code(200)
                .msg("成功")
                .date(null)
                .build();
    }

    public static <T> ResultJson<T> success(T date){
        return ResultJson.<T>builder()
                .code(200)
                .msg("成功")
                .date(date)
                .build();
    }

    public static <T> ResultJson<?> error(String msg){
        return ResultJson.builder()
                .code(500)
                .msg(msg)
                .date(null)
                .build();
    }

    public static <T> ResultJson<Object> error(int code,String msg){
        return ResultJson.builder()
                .code(code)
                .msg(msg)
                .date(null)
                .build();
    }

}
