package com.example.ticketbookingapp.validators;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class BindingValidator {

    public static void validateEntityOrThrowException(BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errMessage = new StringBuilder("Validation error(s): ");
            for (ObjectError err : result.getAllErrors())
                errMessage.append(err.getDefaultMessage()).append(" ");
            throw new IllegalArgumentException(errMessage.toString());
        }
    }
}
