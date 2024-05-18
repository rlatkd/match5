package site.match5.domain.paymentHistory.repository;

import site.match5.domain.paymentHistory.dto.PaymentHistory;

public interface PaymentRepository {
    // 단일 - 결제내역 생성
    void savePaymentHistory(PaymentHistory paymentHistory);
}
