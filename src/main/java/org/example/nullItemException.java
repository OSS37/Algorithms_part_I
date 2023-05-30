package org.example;

public class nullItemException extends RuntimeException {
    public nullItemException() {
    }

    public nullItemException(String message) {
        super(message);
    }

    public nullItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public nullItemException(Throwable cause) {
        super(cause);
    }

    public nullItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
