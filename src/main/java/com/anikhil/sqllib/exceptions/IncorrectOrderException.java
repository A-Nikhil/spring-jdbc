package com.anikhil.sqllib.exceptions;

public class IncorrectOrderException extends Exception {
    public IncorrectOrderException() {
    }

    public IncorrectOrderException(String keyword) {
        super(keyword + " should not have any predecessors");
    }

    public IncorrectOrderException(String pre, String keyword, boolean notAcceptedError) {
        super(String.format("%s keyword should%s exist before calling %s",
                pre,
                (notAcceptedError ? " not" : ""),
                keyword));
    }
}
