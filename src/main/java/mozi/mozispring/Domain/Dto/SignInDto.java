package mozi.mozispring.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    private String email;   // 사용자 아이디
    private String password;// 사용자 패스워드
    private String name;    // 사용자 이름
}
