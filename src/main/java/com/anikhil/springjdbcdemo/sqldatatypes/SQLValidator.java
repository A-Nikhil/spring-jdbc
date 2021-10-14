package com.anikhil.springjdbcdemo.sqldatatypes;

import org.springframework.stereotype.Component;

@Component
public interface SQLValidator {
    boolean isAcceptable(Object object);
}
