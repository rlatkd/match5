package site.match5.global.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import site.match5.global.validation.annotation.Location;

import java.util.Arrays;
import java.util.List;

public class LocationValidator implements ConstraintValidator<Location, String> {

    private static final List<String> ALLOWED_LOCATIONS = Arrays.asList(
            "송파구", "영등포구", "은평구", "강동구", "노원구", "도봉구", "양천구"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // null 값이나 빈 문자열이 유효하지 않음
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        // 허용된 목록에 포함되어 있는지 확인
        return ALLOWED_LOCATIONS.contains(value);
    }
}
