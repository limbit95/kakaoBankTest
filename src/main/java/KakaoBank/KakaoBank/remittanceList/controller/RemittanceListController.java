package KakaoBank.KakaoBank.remittanceList.controller;

import KakaoBank.KakaoBank.account.domain.Account;
import KakaoBank.KakaoBank.account.service.AccountService;
import KakaoBank.KakaoBank.customer.domain.User;
import KakaoBank.KakaoBank.customer.service.UserService;
import KakaoBank.KakaoBank.remittanceList.domain.RemittanceList;
import KakaoBank.KakaoBank.remittanceList.domain.RemittanceListDto;
import KakaoBank.KakaoBank.remittanceList.service.RemittanceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
public class RemittanceListController {

    private final RemittanceListService remittanceListService;
    private final UserService userService;
    private final AccountService accountService;


    @Autowired
    public RemittanceListController(RemittanceListService remittanceListService, UserService userService, AccountService accountService) {
        this.remittanceListService = remittanceListService;
        this.userService = userService;
        this.accountService = accountService;
    }




    @PostMapping("/remit")
    @ResponseBody
    public HttpStatus remit(@RequestBody RemittanceListDto remittanceListDto){
        User user = userService.findByEmail(remittanceListDto.getEmail());
//        파입업로드 여부 조회하도록 user정보 조회 : findByemail;
        Account account = accountService.findByAccountNumber(remittanceListDto.getAccountNumber());

        // 송금 정보 /송금인/수취인/송금액(달러기준)/송금일/송금인id/송금계좌/주소/우편번호/환율/
        RemittanceList remittanceList = RemittanceList.builder()
                .sender(remittanceListDto.getSender())
                .reciever(remittanceListDto.getReciever())
                .remittance_amount_USD(remittanceListDto.getRemittance_amount_USD())
                .remittance_date(LocalDateTime.now())
                .user(user)
                .account(account)
                .address(remittanceListDto.getAddress())
                .zipCode(remittanceListDto.getZipCode())
                .rateKRW(remittanceListDto.getRateKRW())
                .build();



        // 증빙문서 업로드 여부 체크
        if(remittanceList.getRemittance_amount_USD().compareTo(BigDecimal.valueOf(5000)) == 0 ||
                remittanceList.getRemittance_amount_USD().compareTo(BigDecimal.valueOf(5000)) == 1){
            if(user.getFileUploadYn().equals("n")){
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR).getStatusCode();
            }
        }

        // 5000불 이하일 때 수수료 5천원, 5000불 이상일 때 수수료 1만원 추가
        if(remittanceList.getRemittance_amount_USD().compareTo(BigDecimal.valueOf(5000)) == -1){
            remittanceList.setCharge(BigDecimal.valueOf(5000));
        } else if(remittanceList.getRemittance_amount_USD().compareTo(BigDecimal.valueOf(5000)) == 0 ||
                remittanceList.getRemittance_amount_USD().compareTo(BigDecimal.valueOf(5000)) == 1){
            remittanceList.setCharge(BigDecimal.valueOf(10000));
        }

        // 총 예상 비용
        remittanceList.setTotalAmount(remittanceList.getRemittance_amount_USD().multiply(remittanceList.getRateKRW()).add(remittanceList.getCharge()));


        // 통장 잔액보다 송금 금액이 클 경우 송금 불가 if문
        if(account.getAmount().compareTo(remittanceList.getRemittance_amount_USD()
                .multiply(remittanceList.getRateKRW())) == -1){
            String context = "<header> <h1><span>존재하지 않는 화면입니다.</span></h1></header>";
            return new ResponseEntity<String>(context, HttpStatus.INTERNAL_SERVER_ERROR).getStatusCode();
        }
        else {
            account.setAmount(account.getAmount().subtract(remittanceList.getTotalAmount()));

            accountService.create(account);
            remittanceListService.save(remittanceList);
        }

        return null;
    }



}
