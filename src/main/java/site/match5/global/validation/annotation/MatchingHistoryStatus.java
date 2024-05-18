package site.match5.global.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import site.match5.global.validation.validator.LocationValidator;
import site.match5.global.validation.validator.MatchingHistoryStatusValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD}) // 해당 어노테이션을 필드에만 선언 가능함
@Retention(RetentionPolicy.RUNTIME) //해당 어노테이션이 유지되는 시간으로써 런타임까지 유효함
@Constraint(validatedBy = MatchingHistoryStatusValidator.class) // 해당 Validator를 통해 유효성 검사를 진행함
public @interface MatchingHistoryStatus {
    String message() default "입력이 잘못되었습니다. 0 = 결제전, 1 =결제후,경기전 2= 결제후,경기후 , 3= 매칭취소"; // 유효하지 않을 경우 반환할 메세지

    Class<?>[] groups() default {}; //유효성 검증이 진행될 그룹

    Class<? extends Payload>[] payload() default {}; // 유효성 검증 시에 전달할 메타 정보

}
