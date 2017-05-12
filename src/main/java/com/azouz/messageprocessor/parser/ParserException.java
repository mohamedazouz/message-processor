package com.azouz.messageprocessor.parser;

/**
 * @author mazouz
 */
public class ParserException extends Exception {
    private static final long serialVersionUID = -1871030602520817915L;

    public ParserException() {
    }

    public ParserException(final String message) {
        super(message);
    }

    public ParserException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ParserException(final Throwable cause) {
        super(cause);
    }

    public ParserException(final String message, final Throwable cause, final boolean enableSuppression, final
    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
