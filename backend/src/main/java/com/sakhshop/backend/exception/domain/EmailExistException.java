package com.sakhshop.backend.exception.domain;

import java.io.Serial;
import java.io.Serializable;

public class EmailExistException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = -8887834413755296543L;

    public EmailExistException(String message) {
        super(message);
    }

}
