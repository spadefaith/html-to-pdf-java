package com.pdf.commons.dto;

public class BaseAPIErrorResponse extends BaseAPIResponse {

    private String error;
    public BaseAPIErrorResponse(String error) {
        super(null);
        this.error = error;
        super.setSuccess(false);
    }
}
