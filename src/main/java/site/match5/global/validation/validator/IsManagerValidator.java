package site.match5.global.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import site.match5.global.validation.annotation.IsManager;
import site.match5.global.validation.annotation.UserReviewRate;

public class IsManagerValidator implements ConstraintValidator<IsManager, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && (value == 0 || value == 1);
    }
}
