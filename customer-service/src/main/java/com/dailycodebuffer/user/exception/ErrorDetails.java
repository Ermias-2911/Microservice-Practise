package com.dailycodebuffer.user.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Map;

// We add @JsonInclude(JsonInclude.Include.NON_NULL)
// when jacson build this object, it only build on not null object, so validationError {} will not show up when
// there is no error

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {
    private Date date;
    private String message;
    private String details;
    private Map<String , String> validationError;

    public ErrorDetails(Date date, String message, String details) {
        this.date = date;
        this.message = message;
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Map<String, String> getValidationError() {
        return validationError;
    }

    public void setValidationError(Map<String, String> validationError) {
        this.validationError = validationError;
    }
}
