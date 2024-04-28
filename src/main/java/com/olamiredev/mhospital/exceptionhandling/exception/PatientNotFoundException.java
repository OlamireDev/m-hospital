package com.olamiredev.mhospital.exceptionhandling.exception;

public class PatientNotFoundException extends Exception{

    public PatientNotFoundException(Long id) {
        super(id.toString());
    }

}
