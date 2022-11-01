package com.luxoft.springadvanced.beanvalidation.error;

import java.util.Set;

public class PersonUnSupportedFieldPatchException extends RuntimeException {

    public PersonUnSupportedFieldPatchException(Set<String> keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }

}
