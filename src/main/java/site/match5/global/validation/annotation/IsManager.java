package site.match5.global.validation.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import site.match5.global.validation.validator.IsManagerValidator;
import site.match5.global.validation.validator.UserReviewRateValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD}) // 해당 어노테이션을 필드에만 선언 가능함
@Retention(RetentionPolicy.RUNTIME) //해당 어노테이션이 유지되는 시간으로써 런타임까지 유효함
@Constraint(validatedBy = IsManagerValidator.class) // 해당 Validator를 통해 유효성 검사를 진행함
public @interface IsManager {

    String message() default "유효하지않은 입력값입니다. 아님 = 0,관리자 = 1 만 입력가능"; // 유효하지 않을 경우 반환할 메세지

    Class<?>[] groups() default { }; //유효성 검증이 진행될 그룹

    Class<? extends Payload>[] payload() default { }; // 유효성 검증 시에 전달할 메타 정보
}
