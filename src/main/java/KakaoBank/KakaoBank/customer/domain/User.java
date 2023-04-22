package KakaoBank.KakaoBank.customer.domain;

import KakaoBank.KakaoBank.account.domain.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String name;
    @Column(length = 50)
    private String email;
    private String password;

    // 파일 업로드 여부
    @Setter
    private String fileUploadYn;


    @OneToMany(mappedBy = "user")
    private List<Account> accounts = new ArrayList<>();

    @Builder
    public User(String name, String email, String password, String fileUploadYn){
        this.name = name;
        this.email = email;
        this.password = password;
        this.fileUploadYn = fileUploadYn;
    }

}