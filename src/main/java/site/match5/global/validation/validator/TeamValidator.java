package site.match5.global.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import site.match5.global.validation.annotation.Team;

import java.util.Arrays;
import java.util.List;

public class TeamValidator implements ConstraintValidator<Team,String> {

    private static final List<String> ALLOWED_TEAMS = Arrays.asList(
            "H","A"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // null 값이나 빈 문자열이 유효하지 않음
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        // 허용된 목록에 포함되어 있는지 확인
        return ALLOWED_TEAMS.contains(value);
    }
}
