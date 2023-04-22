package KakaoBank.KakaoBank.account.controller;

import KakaoBank.KakaoBank.account.domain.Account;
import KakaoBank.KakaoBank.account.domain.AccountDto;
import KakaoBank.KakaoBank.account.service.AccountService;
import KakaoBank.KakaoBank.customer.domain.User;
import KakaoBank.KakaoBank.customer.service.UserService;
import KakaoBank.KakaoBank.remittanceList.service.RemittanceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;
    private final RemittanceListService remittanceListService;

    @Autowired
    public AccountController(AccountService accountService, UserService userService, RemittanceListService remittanceListService) {
        this.accountService = accountService;
        this.userService = userService;
        this.remittanceListService = remittanceListService;
    }

    @PostMapping("/accountcreate")
    @ResponseBody
    public void accountcreate(@RequestBody AccountDto accountDto){
        User user = userService.findByEmail(accountDto.getEmail());

        Account account = Account.builder()
                .accountNumber(accountDto.getAccountNumber())
                .amount(accountDto.getAmount())
                .user(user)
                .userGrade(accountDto.getUserGrade())
                .build();

        accountService.create(account);
    }





}
