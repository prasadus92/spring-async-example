package com.welt.data.controller.errors;

import com.welt.data.controller.errors.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(RestClientException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> restClientError(RestClientException ex) {

        log.error("REST Client Error", ex);

        ErrorDto error = new ErrorDto();
        error.setError(ErrorConstants.EXTERNAL_API_ERROR);
        error.setMessage(ex.getMessage());
        if(ex instanceof HttpClientErrorException) {
            HttpStatus httpStatus = ((HttpClientErrorException) ex).getStatusCode();
            error.setStatus(httpStatus.value());
            return new ResponseEntity<>(error, httpStatus);
        }

        if(ex instanceof RestClientResponseException) {
            int httpStatusCode = ((RestClientResponseException) ex).getRawStatusCode();
            error.setStatus(httpStatusCode);
            return new ResponseEntity<>(error, HttpStatus.valueOf(httpStatusCode));
        }
        return new ResponseEntity<>(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ErrorConstants.INTERNAL_SERVER_ERROR, ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
