package KakaoBank.KakaoBank.customer.controller;


import KakaoBank.KakaoBank.customer.domain.User;
import KakaoBank.KakaoBank.customer.domain.UserDto;
import KakaoBank.KakaoBank.customer.service.UserService;
import KakaoBank.KakaoBank.remittanceList.service.RemittanceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserService userService;
    private final RemittanceListService remittanceListService;

    @Autowired
    public UserController(UserService userService, RemittanceListService remittanceListService, RemittanceListService remittanceListService1) {
        this.userService = userService;
        this.remittanceListService = remittanceListService1;
    }


    @PostMapping("/user")
    @ResponseBody
    public void save(@RequestBody UserDto userDto){
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .fileUploadYn(userDto.getFileUploadYn())
                .build();

        userService.save(user);
    }





}
