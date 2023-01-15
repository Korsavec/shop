package com.sakhshop.backend.exception.domain;

import java.io.Serial;
import java.io.Serializable;

public class UserNotFoundException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = 4927432165423890781L;

    public UserNotFoundException(String message) {
        super(message);
    }

}
