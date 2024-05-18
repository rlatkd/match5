package site.match5.domain.paymentHistory.repository;

import org.apache.ibatis.annotations.Mapper;
import site.match5.domain.paymentHistory.dto.PaymentHistory;

@Mapper
public interface PaymentMapper {
    // 단일 - 결제내역 생성
    void savePaymentHistory(PaymentHistory paymentHistory);
}
