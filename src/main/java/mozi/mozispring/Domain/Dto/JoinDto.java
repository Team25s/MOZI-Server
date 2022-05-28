package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "회원가입 정보 반환")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JoinDto {
    @ApiModelProperty(value = "메시지", notes = "메시지", required = true, example="회원가입에 성공했습니다.")
    private String message;

    @ApiModelProperty(value = "유저 id", notes = "유저 id", required = true, example="7")
    private Long userId;
}
