package com.acensi.bot.controller;

import com.acensi.bot.http.ApiProblem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
@Validated
@Slf4j
public class ControllerExceptionHandler {


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ApiProblem> gererException(final Exception e) {
        log.error(e.getMessage());
        log.error(String.valueOf(e.getCause()));
        ApiProblem problem = new ApiProblem(500,e.getMessage());
        return  ResponseEntity.status(500).body(problem);
    }


    @ExceptionHandler({HttpClientErrorException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<ApiProblem> gererNonAuth(final HttpClientErrorException e) {
        log.error(e.getMessage());
        log.error(String.valueOf(e.getCause()));
        ApiProblem problem = new ApiProblem(401,"Non autorise");
        return  ResponseEntity.status(401).body(problem);
    }


    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ApiProblem> gererIllegalArgException(final IllegalArgumentException e) {
        log.error(e.getMessage());
        log.error(String.valueOf(e.getCause()));
        ApiProblem problem = new ApiProblem(HttpStatus.BAD_REQUEST.value(),e.getMessage());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problem);
    }




}
