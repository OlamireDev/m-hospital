package com.olamiredev.mhospital.exceptionhandling.exception;

public class RestrictedAccessException extends Exception {

    public RestrictedAccessException(String staffUUID, Long patientId){
        super(staffUUID + ","+patientId);
    }

}
