package org.example;

public class storageIsFullException extends RuntimeException {
    public storageIsFullException() {
    }

    public storageIsFullException(String message) {
        super(message);
    }

    public storageIsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public storageIsFullException(Throwable cause) {
        super(cause);
    }

    public storageIsFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
