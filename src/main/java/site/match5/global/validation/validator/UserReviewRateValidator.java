package site.match5.global.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import site.match5.global.validation.annotation.UserReviewRate;

// 유저 평가 점수 validator
public class UserReviewRateValidator implements ConstraintValidator<UserReviewRate, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && (value == -1 || value == 2 || value == 1);
    }
}
