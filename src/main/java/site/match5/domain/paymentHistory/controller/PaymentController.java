package site.match5.domain.paymentHistory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.match5.domain.paymentHistory.dto.PaymentHistory;
import site.match5.domain.paymentHistory.service.PaymentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    // 단일 - 결제내역 생성

    @PostMapping("")
    ResponseEntity<String> savePaymentHistory(@Valid @RequestBody PaymentHistory paymentHistory){
        paymentService.savePaymentHistory(paymentHistory);
        return ResponseEntity.ok("success");
    }

}
