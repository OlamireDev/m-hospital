package com.olamiredev.mhospital.exceptionhandling.exception;

public class HospitalNotFoundException extends Exception {

    public HospitalNotFoundException(Long id){
        super(id.toString());
    }
}
