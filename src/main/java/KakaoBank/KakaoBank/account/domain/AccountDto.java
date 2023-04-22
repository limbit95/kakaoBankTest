package KakaoBank.KakaoBank.account.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class AccountDto {

    private int accountNumber;
    private BigDecimal amount;
    private String email;
    private String userGrade;
}
