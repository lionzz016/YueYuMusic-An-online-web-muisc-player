package com.cjp.musiclibrary.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CJP
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<E> {
    private String code;
    private String msg;
    private E object;

    public static <E> Result<E> success() {
        return new Result<>("200", "request accept", null);
    }

    public static <E> Result<E> success(E object) {
        return new Result<>("200", "request accept", object);
    }

    public static <E> Result<E> success(String code, String msg, E object) {
        return new Result<>(code, msg, object);
    }

    public static <E> Result<E> error() {
        return new Result<>("400", "request denied", null);
    }

    public static <E> Result<E> error(String code, String msg) {
        return new Result<>(code, msg, null);
    }

}
