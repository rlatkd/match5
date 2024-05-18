package site.match5.global.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public abstract class ErrorCode {
    /*
    각 도메인별 에러코드를 작성하기 위해 추상클래스 생성 - 하나의 에러코드 파일에서 에러코드를 만들면 conflict등 다양한 문제가 발생 , 다형성을 이용하여 유연하게 에러코드 관리
    공통 로직은 중앙에서 관리 (CommonErrorCode), 도메인별 ErrorCode는 각각의 구현체에서 관리 그러므로  충돌 가능성이 줄어든다.
     */
    private final HttpStatus status; // http 상태코드
    private final String code;//에러코드
    private final String message;//에러메시지
}
