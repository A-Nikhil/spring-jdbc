package com.anikhil.sqllib.exceptions;

public class IncorrectOrderException extends Exception {
    public IncorrectOrderException() {
    }

    public IncorrectOrderException(String message) {
        super(message);
    }

    public IncorrectOrderException(String pre, String keyword, boolean notAcceptedError) {
        this(String.format("%s keyword should%s exist before calling %s",
                pre,
                (notAcceptedError ? " not" : ""),
                keyword));
    }
}
