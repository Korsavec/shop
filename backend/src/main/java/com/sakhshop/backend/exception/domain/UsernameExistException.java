package com.sakhshop.backend.exception.domain;

import java.io.Serial;
import java.io.Serializable;

public class UsernameExistException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = 1383986742396545620L;

    public UsernameExistException(String message) {
        super(message);
    }

}
