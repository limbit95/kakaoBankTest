package KakaoBank.KakaoBank.remittanceList.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class RemittanceListDto {

    private String sender;
    private String reciever;

    private BigDecimal remittance_amount_USD;
    private BigDecimal rateKRW;

    private String remittance_date;
    private String email;
    private int accountNumber;
    private String address;
    private int zipCode;


}
