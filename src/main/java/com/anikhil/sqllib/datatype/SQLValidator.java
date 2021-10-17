package com.anikhil.sqllib.datatype;

import org.springframework.stereotype.Component;

@Component
public interface SQLValidator {
    boolean isAcceptable(Object object);
}
