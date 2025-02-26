package com.pdf.config;


import com.pdf.commons.dto.BaseAPIErrorResponse;
import com.pdf.commons.enums.ExceptionCode;
import com.pdf.exceptions.PdfGenerationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public static String getMessage(Exception e, String defaultMessage){
        return (e != null && e.getMessage() != null && !e.getMessage().isEmpty())
                ? e.getMessage()
                : defaultMessage;
    }
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResponseEntity<BaseAPIErrorResponse> handleNullPointerException(NullPointerException e) {
        var exceptionCode = ExceptionCode.BAD_REQUEST;
        String message = getMessage(e, exceptionCode.getDescription());
        log.error(message);
        return  new ResponseEntity<>(
                new BaseAPIErrorResponse(message), exceptionCode.getHttpStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<BaseAPIErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        var exceptionCode = ExceptionCode.BAD_REQUEST;
        String message = getMessage(e, exceptionCode.getDescription());
        log.error(message);
        e.printStackTrace();
        return  new ResponseEntity<>(
                new BaseAPIErrorResponse(message), exceptionCode.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<BaseAPIErrorResponse> argumentNotValidException(MethodArgumentNotValidException ex){
        try {
            StringBuilder errorMessages = new StringBuilder();
            for (ObjectError error : ex.getBindingResult().getAllErrors()) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errorMessages.append(fieldName).append("=").append(errorMessage).append(", ");
            }
            log.error(errorMessages.toString());
            var exceptionCode = ExceptionCode.BAD_REQUEST;
            return new ResponseEntity<>(
                    new BaseAPIErrorResponse(errorMessages.toString()),exceptionCode.getHttpStatus());
        } catch(Exception e){
            var exceptionCode = ExceptionCode.BAD_REQUEST;
            return  new ResponseEntity<>(
                    new BaseAPIErrorResponse(e.getMessage()), exceptionCode.getHttpStatus());
        }
    }

    @ExceptionHandler(PdfGenerationException.class)
    @ResponseBody
    public ResponseEntity<BaseAPIErrorResponse> handleApException(PdfGenerationException e) {

        if(e == null){
            return new ResponseEntity<>(
                    new BaseAPIErrorResponse(ExceptionCode.AP_EXEPTION.getDescription()), ExceptionCode.AP_EXEPTION.getHttpStatus());
        }


        var exceptionCode = ExceptionCode.AP_EXEPTION;
        String message = getMessage(e, exceptionCode.getDescription());
        log.error(message);
        HttpStatus status = e.getStatus() != null ? e.getStatus() : exceptionCode.getHttpStatus();
        return  new ResponseEntity<>(
                new BaseAPIErrorResponse(message),status);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<BaseAPIErrorResponse> handleRuntimeException(RuntimeException e) {
        var exceptionCode = ExceptionCode.INTERNAL_SERVER_ERROR;
        String message = getMessage(e, exceptionCode.getDescription());
        log.error(message, e);
        return  new ResponseEntity<>(
                new BaseAPIErrorResponse(message), exceptionCode.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<BaseAPIErrorResponse> handleException(Exception e) {
        var exceptionCode = ExceptionCode.INTERNAL_SERVER_ERROR;
        String message = getMessage(e, exceptionCode.getDescription());
        log.error(message);
        return  new ResponseEntity<>(
                new BaseAPIErrorResponse(message), exceptionCode.getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<BaseAPIErrorResponse> handleJsonParseError(HttpMessageNotReadableException e) {
        var exceptionCode = ExceptionCode.BAD_REQUEST;
        String message = getMessage(e, exceptionCode.getDescription());
        log.error(message);
        if(message != null && message.contains("JSON parse error:")){
            message = message.replaceFirst("^JSON parse error: ", "");
        }
        return  new ResponseEntity<>(
                new BaseAPIErrorResponse(message), exceptionCode.getHttpStatus());
    }
}
