package site.match5.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import site.match5.global.exception.errorCode.ErrorCode;
import site.match5.global.exception.customException.BusinessException;

import java.util.NoSuchElementException;

import static site.match5.global.exception.errorCode.CommonErrorCode.*;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    /*
    @Valid 또는 @Validated로 binding error 발생시 발생하는 예외
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e) {
        log.warn("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    /*
    들어온 json과 객체의 필드 타입이 달라서 발생하는 오류
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        log.warn("handleHttpMessageNotReadableException", e);
        final ErrorResponse response = ErrorResponse.from(INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }


    /*
     type이 일치하지 않아 binding 못할경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        final ErrorResponse response = ErrorResponse.from(INVALID_TYPE_VALUE);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    /*
    지원하지 않은 Http Method방식으로 호출할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.from(METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    /**
     * 데이터베이스 무결성 에러 DataIntegrityViolationException 외래키깂을 저장할때 , 없는 데이터를 저장하려고하면 발생하는 에러
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException e) {
        log.error("handleDataIntegrityViolationException", e);
        final ErrorResponse response = ErrorResponse.from(INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    /*
    요청한 데이터가 없을때
     */
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(
            NoSuchElementException e) {
        log.error("handleNoSuchElementException", e);
        final ErrorResponse response = ErrorResponse.from(NO_SUCH_ELEMENT);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }


    @ExceptionHandler(BusinessException.class) //비즈니스로직에서 던져지는,발생되는 예외들에 대한 처리 (validation오류들과 같은)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.error("handle내부로직에러(BusinessException)", e);
        final ErrorCode code = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.from(code);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(HandlerMethodValidationException.class) // List나 다른 컬렉션 형태의 객체의 경우에는 컬렉션 내의 각 객체에 대한 유효성 검사가 이뤄지고  이때 발생하는 예외는 주로 HandlerMethodValidationException
    protected ResponseEntity<ErrorResponse> handleHandlerMethodValidationException(HandlerMethodValidationException e) {
        log.error("handleHandlerMethodValidationException", e);
        final ErrorResponse response = ErrorResponse.from(INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

//    존재하지않는 url 을 요청하였을때
    @ExceptionHandler(NoResourceFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        log.error("handleNoResourceFoundException", e);
        final ErrorResponse response = ErrorResponse.from(INVALID_URL);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(Exception.class) // 위의 예외들에서 걸러지지않은 나머지 예외들에 대한 처리입니다, 클라이언트에서 자세한 오류내용을 알수있도록 Validation을 추가해주세요
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handle 지정하지않은 나머지 Exception", e);
        final ErrorResponse response = ErrorResponse.from(INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }


}


/*
메서드가 protected 접근 제어자로 선언된 이유는 해당 메서드들이 외부에서 직접 호출되는 것이 아니라, 내부적으로 호출되도록 설계되었기 때문입니다.
일반적으로 @RestControllerAdvice에 정의된 메서드들은 해당 애플리케이션 내에서 발생하는 예외를 처리하기 위해 내부적으로 호출됩니다. 이러한 메서드들은 외부에서 직접 호출될 필요가 없으므로 private이나 protected 접근 제어자를 사용하는 것이 일반적입니다.
protected는 같은 패키지 내에 있거나 해당 클래스를 상속받은 클래스에서 접근이 가능합니다. 이렇게 선언하면 향후 해당 클래스를 상속받아 예외 처리 로직을 확장하거나 재정의할 수 있습니다.
반면에 public 접근 제어자를 사용하면 외부에서도 해당 메서드에 접근할 수 있게 되어 의도치 않은 호출이 발생할 수 있습니다.
따라서 @RestControllerAdvice에 정의된 예외 처리 메서드들은 protected 접근 제어자를 사용하여 내부적으로만 호출되도록 하고, 외부에서는 직접 호출할 수 없도록 하는 것이 일반적인 관행입니다.
 */