package com.cj.sampleproject.three.exception;

public enum ErrorCode {

    HTTP001("HTTP001"),     //Http call exception
    SVR001("SVR001"),       //Server data retrieval exception
    SVEX001("SVEX001");     //service internal exception


    private final String value;

    ErrorCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
