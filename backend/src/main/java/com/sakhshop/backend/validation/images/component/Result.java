package com.sakhshop.backend.validation.images.component;

public class Result {

    private final StringBuilder messagesSb;

    private Boolean isOk;

    private Type resultType;


    public Result() {
        this.messagesSb = new StringBuilder();
        this.isOk = true;
        this.resultType = Type.OK;
    }

    public StringBuilder getMessagesSb() {
        return messagesSb;
    }

    public Boolean getOk() {
        return isOk;
    }

    public void setOk(Boolean ok) {
        isOk = ok;
    }

    public Type getResultType() {
        return resultType;
    }

    public void setResultType(Type resultType) {
        this.resultType = resultType;
    }


}
