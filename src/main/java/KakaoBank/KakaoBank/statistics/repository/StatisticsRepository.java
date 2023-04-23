package KakaoBank.KakaoBank.statistics.repository;

import KakaoBank.KakaoBank.statistics.domain.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {



}
