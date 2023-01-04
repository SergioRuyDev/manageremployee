package com.sergioruy.manageremployee.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),
    EMPLOYEE_EMAIL_EXIST("/employee-email-exist", "Employee email exist" ),
    INTERNAL_SERVER_ERROR("/internal-server-error", "Internal Server Error");

    private String title;
    private String uri;

    ProblemType(String uri, String title) {
        this.uri = "https://manageremployee.com" + uri;
        this.title = title;
    }
}
