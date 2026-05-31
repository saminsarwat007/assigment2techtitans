package com.techtitans.studentrecords.exception;

public class DuplicateMatricException extends RuntimeException {

    public DuplicateMatricException(String matricNo) {
        super("A student with matric number " + matricNo + " already exists");
    }
}
