package KakaoBank.KakaoBank.account.service;


import KakaoBank.KakaoBank.account.domain.Account;
import KakaoBank.KakaoBank.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void create(Account account){
        accountRepository.save(account);
    }

    public Account findByAccountNumber(int accountNumber){
        return accountRepository.findByAccountNumber(accountNumber).orElse(null);
    }

//    public void withdraw(int amount){
//
//    }

}
