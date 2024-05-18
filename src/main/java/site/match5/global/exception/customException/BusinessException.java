package site.match5.global.exception.customException;

import lombok.Getter;
import site.match5.global.exception.errorCode.ErrorCode;


@Getter
public class BusinessException extends RuntimeException{ //api 내부동작할때(비즈니스 로직에서) 발생하는 일반적인 예외들

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
