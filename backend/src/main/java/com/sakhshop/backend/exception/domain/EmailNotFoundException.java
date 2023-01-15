package com.sakhshop.backend.exception.domain;

import java.io.Serial;
import java.io.Serializable;

public class EmailNotFoundException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = 1555817672520092250L;

    public EmailNotFoundException(String message) {
        super(message);
    }

}
