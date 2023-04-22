package KakaoBank.KakaoBank.remittanceList.repository;

import KakaoBank.KakaoBank.remittanceList.domain.RemittanceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemittanceListRepository extends JpaRepository<RemittanceList, Long> {



}
