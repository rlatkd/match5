package site.match5.global.exception.errorCode;

import org.springframework.http.HttpStatus;

public class CommonErrorCode extends ErrorCode {

    //Common -> http 요청시 발생할만한 예외
    public static final CommonErrorCode INVALID_INPUT_VALUE = new CommonErrorCode(HttpStatus.BAD_REQUEST, "Common-001", "유효하지않은 입력값입니다.");
    public static final CommonErrorCode METHOD_NOT_ALLOWED = new CommonErrorCode(HttpStatus.METHOD_NOT_ALLOWED, "Common-002", "유효하지않은 Http Method 입니다.");
    public static final CommonErrorCode ENTITY_NOT_FOUND = new CommonErrorCode(HttpStatus.BAD_REQUEST,"Common-003", " Entity Not Found");
    public static final CommonErrorCode INTERNAL_SERVER_ERROR = new CommonErrorCode(HttpStatus.INTERNAL_SERVER_ERROR, "Common-004", "서버 에러입니다. 관리자에게 문의하세요");
    public static final CommonErrorCode INVALID_TYPE_VALUE = new CommonErrorCode(HttpStatus.BAD_REQUEST,"Common-005", "전달한 데이터의 타입을 확인해주세요 ");
    public static final CommonErrorCode HANDLE_ACCESS_DENIED = new CommonErrorCode(HttpStatus.FORBIDDEN, "Common-006", "접근이 거부되었습니다.");
    public static final CommonErrorCode NO_SUCH_ELEMENT = new CommonErrorCode(HttpStatus.BAD_REQUEST, "Common-007", "요청하신 데이터가 존재하지않습니다.");
    public static final CommonErrorCode INVALID_URL = new CommonErrorCode(HttpStatus.BAD_REQUEST, "Common-008", "유효하지않은 경로입니다.");
    public static final CommonErrorCode ALARM_ERROR = new CommonErrorCode(HttpStatus.INTERNAL_SERVER_ERROR, "Common-009", "알람오류,개발자에게 문의하세요");

    // update,delete가 적용된 row가 없음
    public static final CommonErrorCode SQL_FAIL = new CommonErrorCode(HttpStatus.INTERNAL_SERVER_ERROR,"SQL-001","sql 실패 : 파라미터를 확인해 주세요 ");

    public CommonErrorCode(HttpStatus status, String code, String message) {
        super(status, code, message);
    }
}
