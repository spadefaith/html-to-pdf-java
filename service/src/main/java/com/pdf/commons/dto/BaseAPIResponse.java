package com.pdf.commons.dto;

import com.pdf.service.helpers.DateTimeHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Slf4j
@AllArgsConstructor
public class BaseAPIResponse {

    private String timestamp;
    private Boolean success;
    private Object response;

    public BaseAPIResponse(Object response) {
        this.response = response;
        this.success = true;
        this.timestamp = generateTimestamp();
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
