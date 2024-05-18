package site.match5.domain.paymentHistory.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistory {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer matchingHistoryId;

}
