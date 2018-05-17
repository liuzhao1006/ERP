package com.ycjt.sx.exception;

/**
 * 作者 : 刘朝,
 * on 2017/7/25,
 * GitHub : https://github.com/liuzhao1006
 */

public class IllegalDataException extends Exception {

    public IllegalDataException() {
    }

    public IllegalDataException(String message) {
        super(message);
    }

    public IllegalDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalDataException(Throwable cause) {
        super(cause);
    }
}
