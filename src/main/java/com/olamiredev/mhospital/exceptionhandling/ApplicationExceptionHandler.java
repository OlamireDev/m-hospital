package com.olamiredev.mhospital.exceptionhandling;

import com.olamiredev.mhospital.exceptionhandling.exception.HospitalNotFoundException;
import com.olamiredev.mhospital.exceptionhandling.exception.PatientNotFoundException;
import com.olamiredev.mhospital.exceptionhandling.exception.RestrictedAccessException;
import com.olamiredev.mhospital.exceptionhandling.exception.StaffNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){
        if(ex instanceof HospitalNotFoundException){
            return new ResponseEntity<>(String.format("Hospital with id %s not found", ex.getMessage()), HttpStatus.NOT_FOUND);
        }
        if(ex instanceof PatientNotFoundException){
            return new ResponseEntity<>(String.format("Patient with is %s not found", ex.getMessage()), HttpStatus.NOT_FOUND);
        }
        if(ex instanceof StaffNotFoundException) {
            return new ResponseEntity<>(String.format("Staff with uuid %s not found", ex.getMessage()), HttpStatus.NOT_FOUND);
        }
        if(ex instanceof RestrictedAccessException) {
            var params = ex.getMessage().split(",");
            return new ResponseEntity<>(String.format("Staff with uuid %s is not in the same hospital as patient with id %s", params[0], params[1]), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
