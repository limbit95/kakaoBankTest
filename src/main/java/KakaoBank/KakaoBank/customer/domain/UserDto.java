package KakaoBank.KakaoBank.customer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
public class UserDto {
    private String name;
    private String email;
    private String password;
    private String fileUploadYn;

}