package site.match5.domain.paymentHistory.service;

import site.match5.domain.paymentHistory.dto.PaymentHistory;

public interface PaymentService {
    // 단일 - 결제내역 생성
    void savePaymentHistory(PaymentHistory paymentHistory);
}
