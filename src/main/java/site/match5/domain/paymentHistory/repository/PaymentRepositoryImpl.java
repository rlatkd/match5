package site.match5.domain.paymentHistory.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.match5.domain.paymentHistory.dto.PaymentHistory;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentMapper dao;

    @Override
    public void savePaymentHistory(PaymentHistory paymentHistory) {
        dao.savePaymentHistory(paymentHistory);
    }
}
