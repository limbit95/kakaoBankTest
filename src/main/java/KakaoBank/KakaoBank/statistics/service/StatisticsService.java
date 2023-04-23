package KakaoBank.KakaoBank.statistics.service;

import KakaoBank.KakaoBank.statistics.domain.Statistics;
import KakaoBank.KakaoBank.statistics.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Autowired
    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public void save(Statistics statistics){
        statisticsRepository.save(statistics);
    }


}
