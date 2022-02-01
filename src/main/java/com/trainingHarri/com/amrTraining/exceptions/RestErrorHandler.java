package com.trainingHarri.com.amrTraining.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestErrorHandler {
        @ExceptionHandler(customExeption.class)
        @ResponseBody
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public com.trainingHarri.com.amrTraining.exceptions.CustomResponse handleSecurityException(customExeption se) {
            com.trainingHarri.com.amrTraining.exceptions.CustomResponse response = new com.trainingHarri.com.amrTraining.exceptions.CustomResponse(se.getErrorMessage());
            return response;
        }
    }


