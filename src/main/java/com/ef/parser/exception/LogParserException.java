package com.ef.parser.exception;

public class LogParserException extends RuntimeException {
    private static final long serialVersionUID = -323887610367497718L;

    public LogParserException(String message) {
        super(message);
    }

    public LogParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
