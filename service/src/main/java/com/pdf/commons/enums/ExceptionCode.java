package com.pdf.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    UNEXPECTED_EXCEPTION("100","Unexpected Error", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("400", "Bad Request", HttpStatus.BAD_REQUEST),
    NOT_FOUND("404", "Not Found", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    AP_EXEPTION("200","Something went wrong",HttpStatus.OK);

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;

}
