package KakaoBank.KakaoBank.remittanceList.service;

import KakaoBank.KakaoBank.remittanceList.domain.RemittanceList;
import KakaoBank.KakaoBank.remittanceList.repository.RemittanceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RemittanceListService {

    private final RemittanceListRepository remittanceListRepository;

    @Autowired
    public RemittanceListService(RemittanceListRepository remittanceListRepository) {
        this.remittanceListRepository = remittanceListRepository;
    }

    public void save(RemittanceList remittanceList){
        remittanceListRepository.save(remittanceList);
    }


}
