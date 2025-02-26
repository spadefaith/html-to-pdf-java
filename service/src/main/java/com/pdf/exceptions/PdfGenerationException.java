package com.pdf.exceptions;

import com.pdf.service.helpers.DateTimeHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Date;


@Slf4j
public class PdfGenerationException extends RuntimeException{
    @Getter
    private final Boolean success;
    @Getter
    private final String timestamp;
    @Getter
    private final String message;
    @Getter
    private final HttpStatus status;

    public PdfGenerationException(String message) {
        super(message);
        this.success = false;
        this.timestamp = generateTimestamp();
        this.message = message;
        this.status = HttpStatus.OK;
    }

    public PdfGenerationException(String message, HttpStatus status) {
        super(message);
        this.success = false;
        this.timestamp = generateTimestamp();
        this.message = message;
        this.status = status;
    }


    private String generateTimestamp() {
        try {
            return DateTimeHelper.formatDateToTimestamp(new Date(), "yyyy-MM-dd HH:mm:ss", null);
        } catch (RuntimeException e) {
            log.error("e: ", e);
            return null;
        }
    }
}
