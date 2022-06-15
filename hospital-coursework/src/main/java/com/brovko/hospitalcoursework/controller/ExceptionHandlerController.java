package com.brovko.hospitalcoursework.controller;

import com.brovko.hospitalcoursework.exception.LogNotFoundException;
import com.brovko.hospitalcoursework.exception.RoleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.DateTimeException;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(LogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> exception(LogNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> exception(RoleException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DateTimeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> exception(DateTimeException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}