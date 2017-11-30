package com.teamUPOD.UPOD.UPOD;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    /**
     * Handle SQLException error case for all endpoints
     * @param sqlException
     * @return
     */
    @ExceptionHandler({SQLException.class})
    public ResponseEntity<String> handleSQLException (SQLException sqlException){
    		return new ResponseEntity<String>("Database error: " + sqlException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
