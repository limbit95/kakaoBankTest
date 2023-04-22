package KakaoBank.KakaoBank.account.domain;

import KakaoBank.KakaoBank.customer.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int accountNumber;

    @Setter
    private BigDecimal amount;

    private String userGrade;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "userId", referencedColumnName = "id")
    private User user;

    @Builder
    public Account(String bankName, int accountNumber, BigDecimal amount, User user, String userGrade){
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.user = user;
        this.userGrade = userGrade;
    }


}
