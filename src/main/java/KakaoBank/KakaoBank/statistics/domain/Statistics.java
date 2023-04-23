package KakaoBank.KakaoBank.statistics.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // USD 기준으로 누적 5만 달러
    private BigDecimal yearTotal;
    private LocalDateTime createTime;

    @Builder
    public Statistics(BigDecimal yearTotal, LocalDateTime createTime){
        this.yearTotal = yearTotal;
        this.createTime = createTime;
    }

}
