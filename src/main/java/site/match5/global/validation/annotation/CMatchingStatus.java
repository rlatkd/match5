package site.match5.global.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import site.match5.global.validation.validator.CMatchingStatusValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD}) // 해당 어노테이션을 파라미터에서 선언 가능함
@Retention(RetentionPolicy.RUNTIME) //해당 어노테이션이 유지되는 시간으로써 런타임까지 유효함
@Constraint(validatedBy = CMatchingStatusValidator.class) // 해당 Validator를 통해 유효성 검사를 진행함
public @interface CMatchingStatus {
    String message() default "유효하지않은 입력값입니다. 0 = 매칭전 , 1 = 매칭중,  2= 매칭완료"; // 유효하지 않을 경우 반환할 메세지

    Class<?>[] groups() default { }; //유효성 검증이 진행될 그룹

    Class<? extends Payload>[] payload() default { }; // 유효성 검증 시에 전달할 메타 정보
}
