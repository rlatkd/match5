package site.match5.domain.paymentHistory.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.paymentHistory.dto.PaymentHistory;
import site.match5.domain.paymentHistory.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void savePaymentHistory(PaymentHistory paymentHistory) {
        paymentRepository.savePaymentHistory(paymentHistory);
    }
}
