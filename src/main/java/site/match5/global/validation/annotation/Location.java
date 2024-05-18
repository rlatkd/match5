package site.match5.global.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import site.match5.global.validation.validator.LocationValidator;
import site.match5.global.validation.validator.UserReviewRateValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD}) // 해당 어노테이션을 필드에만 선언 가능함
@Retention(RetentionPolicy.RUNTIME) //해당 어노테이션이 유지되는 시간으로써 런타임까지 유효함
@Constraint(validatedBy = LocationValidator.class) // 해당 Validator를 통해 유효성 검사를 진행함
public @interface Location {
    String message() default "송파구,영등포구,은평구,강동구,노원구,도봉구,양천구 중에서만 선택하세요."; // 유효하지 않을 경우 반환할 메세지

    Class<?>[] groups() default {}; //유효성 검증이 진행될 그룹

    Class<? extends Payload>[] payload() default {}; // 유효성 검증 시에 전달할 메타 정보
}
