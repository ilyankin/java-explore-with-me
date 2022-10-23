package ru.practicum.ewm.constraint.validator;

import ru.practicum.ewm.constraint.NullOrNotBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {
    public void initialize(NullOrNotBlank parameters) {
        // nothing to do here
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || !value.isBlank();
    }
}
