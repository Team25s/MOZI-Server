package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value="로그인 정보")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogInDto {
    @ApiModelProperty(value = "이메일", notes = "이메일", required = true, example="mozi-25s@gmail.com")
    private String email;
    
    @ApiModelProperty(value = "비밀번호", notes = "비밀번호", required = true, example="1234mozi!!")
    private String password;
}
