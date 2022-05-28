package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value="회원가입 및 회원탈퇴 시 입력 정보")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    @ApiModelProperty(value = "이메일", notes = "이메일", required = true, example="mozi-25s@gmail.com")
    private String email;   // 사용자 이메일

    @ApiModelProperty(value = "비밀번호", notes = "비밀번호", required = true, example="1223mozi!!")
    private String password;// 사용자 패스워드

    @ApiModelProperty(value = "이름", notes = "이름", required = true, example="홍길동")
    private String name;    // 사용자 이름
}
