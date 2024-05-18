package site.match5.global.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import site.match5.global.validation.annotation.MatchingHistoryStatus;

public class MatchingHistoryStatusValidator implements ConstraintValidator<MatchingHistoryStatus, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && (value == 0 || value == 1 || value == 2);
    }
}
