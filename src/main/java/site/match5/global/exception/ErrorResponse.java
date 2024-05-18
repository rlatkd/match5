package site.match5.global.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import site.match5.global.exception.errorCode.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse { //에러응답객체

    private final String message; //에러메시지
    private final int status; // http 상태코드
    private final String code; //에러코드
    private final List<FieldError> errors; // 유효성검증에서 실패했을경우 어떤필드인지,어떤값이들어왔는지,검증오류메시지를 보여주는 커스텀 FieldError 리스트를 받는다.
    //생성자는 접근제한자를 private 로 설정하여 외부에서 생성자 접근을 막는다. 정적 팩토리 메소드를 사용하도록 유도한다.
    private ErrorResponse(final ErrorCode errorCode) { //  다형성을 사용하기위해 ErrorCode 추상클래스객체 변수를 사용한다.
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus().value(); // HTTP 상태코드의 숫자를 받아올수있다.
        this.code = errorCode.getCode();
        this.errors = new ArrayList<>(); // 필드 유효성 에러 결과를 못받을때  null 값으로 응답할순없고, 빈 리스트를 넣어준다.
    }
    private ErrorResponse(final ErrorCode errorCode, final List<FieldError> errors) { //에러코드객체와, 필드에러리스트가 들어왔을때 응답객체 생성
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus().value();
        this.code = errorCode.getCode();
        this.errors = errors;
    }

    // 에러코드를 받아서 객체 생성 , 매개변수가 하나이므로 정적 팩토리 메소드 네이밍 컨벤션을 지키려고 from 으로 지었다.
    public static ErrorResponse from(final ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    //에러코드와 BindingResult 를 받아서 객체생성, 매개변수가 여럿이므로 정적 팩토리 메소드 네이밍 컨벤션을 지키려고 of 로 지었다.
    public static ErrorResponse of(final ErrorCode errorCode, final BindingResult bindingResult) {
        return new ErrorResponse(errorCode, FieldError.listFrom(bindingResult));
    }



    @Getter
    private static class FieldError { //필드에서 발생한 유효성검사 실패에 대한 정보를 저장할 클래스
        String field; // 클래스명.유효성검사 실패한 변수명이 저장될 변수가 저장된다 (ex) member.name
        String value; // 들어온 값
        String reason; // 유효성 검사 조건

        // 클래스가 private 이므로, 즉 외부클래스에서만 사용되므로 필드나 메서드에 private 붙일 필요가 없다.
        FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        //static 으로 선언하여 해당 클래스의 객체생성없이 메소드를 사용가능하다. + 반환타입이 List 이고 매개변수가 하나므로 From listFrom 이라고 작명하였다.
        static List<FieldError> listFrom(final BindingResult bindingResult) { //BindingResult는 검증오류가 발생할 경우 검증오류를 보관하는 객체
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors(); //bindingResult 에 저장되는 springframework 에서 지원하는 FieldError 가져온다
            return fieldErrors.stream() //내가 만든 FieldError 로 매핑한다.
                .map(error -> new FieldError(
                    error.getField(),
                    error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(), // 거부된 값이 있다면 넣어주고 없다면 빈값
                    error.getDefaultMessage()))
                .collect(Collectors.toList()); // 리스트로 만들어서 반환해준다.
        }
    }

}


/*
1.
스프링에서 제공하는 FieldError에는
      {
            "codes": [
                "NotBlank.courtReview.content",
                "NotBlank.content",
                "NotBlank.java.lang.String",
                "NotBlank"
            ],
            "arguments": [
                {
                    "codes": [
                        "courtReview.content",
                        "content"
                    ],
                    "arguments": null,
                    "defaultMessage": "content",
                    "code": "content"
                }
            ],
            "defaultMessage": "공백일 수 없습니다",
            "objectName": "courtReview",
            "field": "content",
            "rejectedValue": "",
            "bindingFailure": false,
            "code": "NotBlank"
        }
다음과같이 제공하는 정보가 많다.
그래서 커스텀 FieldError 클래스를 만들어서 실제 FieldError 에서 필요한 정보만을 가지고온다.

2.
정적 팩토리 메소드를 사용함으로써 객체를 생성하는 메소드의 이름을 부여하여 객체의 생성 목적을 나타낼수있다.
정적팩토리메소드를 사용함으로써 객체 생성을 캡슐화하여 객체 생성 과정을 몰라도[은닉화], 쉽게 객체를 생성 할 수 있다.
정적 팩토리 메소드를 사용하지않았더라면 직접 객체 생성에 필요한 값을 다 넣어줘야하고 , 그것은 내부 구현이 드러나는것이다.

3. FieldError 클래스는 ErrorResponse 클래스 내부에서만 사용할것이므로 내부클래스로 지정하였다.
일반적인 내부 클래스는 외부 클래스의 참조를 가지고 있기 때문에 메모리 누수가 발생한다 ( 가바지 컬렉터가 정리를 안해주기 때문)
내부 클래스를 static 으로 선언한다면 static 클래스는 외부 클래스 참조를 하지않기 때문에 메모리 누수가 없다.
 이는 정적 내부 클래스가 외부 클래스와 독립적으로 존재할 수 있고, 외부 클래스의 인스턴스 생성과 관련이 없는 정적 멤버와 같은 취급을 받기 때문

 */
/*
스프링의 기본 응답 객체는 발생시간,http상태코드,에러메시지,발생한url경로 등을 나타낸다.
커스텀한 에러 응답객체를 만들면
에러 코드, 메시지, 상세 정보 등을 자유롭게 정의하여 개발자 및 클라이언트에게 명확한 정보 제공할 수 있어서 상황에 맞는 다양한 에러 정보 제공 가능하다.
여러 에러 유형에 대한 맞춤 응답 객체 설계 가능하다. (예: 비즈니스 로직 오류, 데이터베이스 오류 등)
일관성 유지: 프로젝트 전체 API 응답 형식 일관성 유지
모든 API 응답에 동일한 구조의 에러 객체 사용하여 응답 예측성 향상
개발 및 유지 관리 용이
정보 보호: 민감한 정보 노출 방지
스택 추적 정보 등 원하지 않는 정보 제외 가능
필요한 에러 정보만 제공하여 보안 강화
개발자 경험 향상: 디버깅 용이
에러 코드 및 메시지로 명확한 오류 원인 파악 가능
상세 정보를 통해 빠른 문제 해결 가능

단점 : 문서화 필요: 사용 방법 및 구조 명확하게 문서화 필요 ,개발자 및 클라이언트가 에러 응답 객체 이해 및 사용 방법 파악 가능하도록 문서 제공해야한다.
하지만 그렇게하면 프로젝트 내 지식 공유 및 유지 관리 용이하다.

---
그렇다면 커스텀 에러 응답 객체에는 어떤것이 들어가야하는가?

기본 제공되는 정보 외에 커스텀 에러 응답 객체에 포함하는 정보는 다음과 같은 가이드라인을 참고하여 선택하는 것이 좋습니다.

필수 정보:
상위 예외 클래스: 발생한 오류의 근본적인 원인을 파악하는 데 도움이 되는 정보입니다.
예를 들어, DataAccessException 또는 BusinessException과 같은 상위 예외 클래스를 포함할 수 있습니다.
예외 메시지: 오류의 구체적인 내용을 나타내는 정보입니다. 기본 제공되는 error 속성과 동일하거나, 더 자세한 정보를 제공할 수 있습니다.
개발자 메시지: 개발자가 오류를 해결하는 데 도움이 되는 추가 정보입니다. 예를 들어, 오류가 발생한 코드 위치, 관련 데이터 등을 포함할 수 있습니다.
 */



