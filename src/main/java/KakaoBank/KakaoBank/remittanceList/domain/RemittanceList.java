package KakaoBank.KakaoBank.remittanceList.domain;

import KakaoBank.KakaoBank.account.domain.Account;
import KakaoBank.KakaoBank.customer.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class RemittanceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    private String reciever;
    private BigDecimal remittance_amount_USD;
    private BigDecimal rateKRW;
    private LocalDateTime remittance_date;

    @Setter
    private BigDecimal charge;
    @Setter
    private BigDecimal totalAmount;

    private String address;
    private int zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "userId", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "AccountId", referencedColumnName = "id")
    private Account account;


    @Builder
    public RemittanceList(String sender, String reciever, BigDecimal remittance_amount_USD,
                          LocalDateTime remittance_date, User user, Account account,
                          String address, int zipCode, BigDecimal rateKRW){
        this.sender = sender;
        this.reciever = reciever;
        this.remittance_amount_USD = remittance_amount_USD;
        this.remittance_date = LocalDateTime.now();
        this.user = user;
        this.account = account;
        this.address = address;
        this.zipCode = zipCode;
        this.rateKRW = rateKRW;
    }



}
